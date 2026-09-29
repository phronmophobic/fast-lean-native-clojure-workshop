(ns build
  (:require [clojure.tools.build.api :as b]
            [clojure.string :as str]))

(def lib 'fastleannative/small3d)
(def version "0.1")
(def class-dir "target/classes")
(def basis* {:project "deps.edn"})
(def jar-file (format "target/%s-%s.jar" (name lib) version))
(def uber-file (format "target/%s-%s-standalone.jar" (name lib) version))

(defn clean [_]
  (b/delete {:path "target"}))


#_(defn uber [_]
  (clean nil)
  (b/copy-dir {:src-dirs ["src" "resources"]
               :target-dir class-dir})
  (compile nil)
  (b/uber {:class-dir class-dir
           :uber-file uber-file
           :basis (b/create-basis basis*)
           :main 'clong.libz}))


(defn uber-qsort [_]
  (clean nil)
  (b/copy-dir {:src-dirs ["src" "resources"]
               :target-dir class-dir})
  (b/compile-clj {:basis (b/create-basis basis*)
                  :ns-compile '[clong.qsort]
                  :class-dir class-dir
                  :jvm-opts ["-Dtech.v3.datatype.graal-native=true"
                             "-Dclojure.compiler.direct-linking=true"
                             "-Dclojure.spec.skip-macros=true"]})
  (b/uber {:class-dir class-dir
           :uber-file uber-file
           :basis (b/create-basis basis*)
           :main 'clong.qsort}))


