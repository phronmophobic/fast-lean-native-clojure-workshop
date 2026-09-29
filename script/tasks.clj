(ns tasks
  (:require [babashka.process :as bp]
            [babashka.fs :as fs]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.test :as test
             :refer [testing]])
  (:import java.io.ByteArrayOutputStream))

(def ^:private system-os*
  (delay
    (let [os-name (System/getProperty "os.name")
          os (cond
               (str/starts-with? (str/lower-case os-name)
                                 "mac")
               "darwin"

               (str/starts-with? (str/lower-case os-name)
                                 "windows")
               "windows"
               
               :else "linux")]
      os)))

(def ^:private system-arch*
  (delay
    (let [arch-name (System/getProperty "os.arch")]
      (case arch-name
        "amd64" "x86-64"
        "x86_64" "x86-64"
        "aarch64" "aarch64"
        "arm64" "arm64"))))
(def ^:private system*
  (delay
    (str @system-os* "-" @system-arch*)))

(defn os []
  @system-os*)

(defn arch []
  @system-arch*)

(defn system-arch []
  @system*)

(defn shared-lib-suffix []
  (case @system-os*
    "linux" "so"
    "darwin" "dylib"))

(def CC (or (System/getenv "CC") "clang"))
(def CXX (or (System/getenv "CXX") "clang++"))

(prn "CC" CC)
(prn "CXX" CXX)

(test/deftest boom
  (let [p @(bp/process {:dir "boom"} "clojure" "-M" "-m" "fastleannative.boom")]
    (test/is (not (zero? (:exit p))) "boom exploded" )
    (test/is (seq (fs/list-dir "boom" "hs_err*"))
             "hs_err* file created")))

(test/deftest hellocljc
  (let [p @(bp/process {:dir "hellocljc"} "clojure" "-T:build" "uber")]
    (test/is (zero? (:exit p)) "build uberjar" ))
  (let [p (bp/process {:dir "hellocljc"}
                      "./compile-native-image.sh")

        p @p]
    (test/is (zero? (:exit p)) "compile-native-image"))
  (let [p @(bp/process {:dir "hellocljc"} "./target/hello-world-native-image")]
    (test/is (zero? (:exit p)) "run test program")))

(test/deftest remorse
  (let [p @(bp/process {:dir "remorse"} "clojure" "-T:build" "uber")]
    (test/is (zero? (:exit p)) "build uberjar" ))
  (let [p (bp/process {:dir "remorse"}
                      "./compile-native-image.sh")

        p @p]
    (test/is (zero? (:exit p)) "compile-native-image"))
  (let [p @(bp/process {:dir "remorse"} "./target/remorse")]
    (test/is (zero? (:exit p)) "run test program")
    (test/is (fs/exists? (fs/file "remorse" "out.edn")) "check for out.edn")))

(test/deftest libfastleannative-c
  (let [cmd (into []
                   (remove nil?)
                   [CC
                    (when (= "linux" (os)) "-fPIC")
                    "-c" "fastleannative.c"
                    "-o" "fastleannative.o"])
        p @(apply bp/process {:dir "libfastleannative-c"} cmd)]
    (test/is (zero? (:exit p)) "compiled"))

  (let [cmd (into []
                  (remove nil?)
                  [CC
                   (case (os)
                     "linux" "-shared"
                     "darwin" "-dynamiclib")
                   "fastleannative.o"
                   "-o" (str "libfastleannative." (shared-lib-suffix))])
        p @(apply bp/process {:dir "libfastleannative-c"} cmd)]
    (test/is (zero? (:exit p)) "created shared library" )))



(test/deftest libfastleannative-clj
  (let [p @(bp/process {:dir "libfastleannative-clj"} "clojure" "-T:build" "uber")]
    (test/is (zero? (:exit p)) "build uberjar" ))
  (let [p (bp/process {:dir "libfastleannative-clj"}
                      "./compile-native-image.sh")

        p @p]
    (test/is (zero? (:exit p)) "compile-native-image"))
  (let [p @(bp/process {:dir "libfastleannative-clj/c"}
                       "./compile.sh")]
    (test/is (zero? (:exit p)) "compile test program"))
  (let [p @(bp/process {:dir "libfastleannative-clj/c"} "./main")]
    (let [baos (ByteArrayOutputStream.)]
      (io/copy (:out p)
               baos)
      (prn (.toString baos "utf-8")))
    (test/is (zero? (:exit p)) "run test program")))


(test/deftest small3d
  (let [p @(bp/process {:dir "small3d/c/third_party"}
                       "./compile.sh")]
    (test/is (zero? (:exit p)) "compile lib"))
  (let [p @(bp/process {:dir "small3d"
                        :out :inherit} "clojure" "-M:project" "-m" "fastleannative.small3d" "1")]
    (test/is (zero? (:exit p)) "run test program" )))

(defn test
  "test"
  {}
  [opts]
  (let [result (test/run-tests 'tasks)]
    (when (not (and (zero? (:error result))
                    (zero? (:fail result))))
      (throw (ex-info "Something failed" {:result result})))))



