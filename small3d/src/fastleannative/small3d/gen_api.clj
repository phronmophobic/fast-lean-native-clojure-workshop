(ns fastleannative.small3d.gen-api
  (:require [clojure.java.io :as io]
            [clojure.pprint :refer [pprint]]
            [clojure.edn :as edn]
            )
  (:import
   java.io.PushbackReader)
  (:gen-class))

(defn ^:private write-edn [w obj]
  (binding [*print-length* nil
            *print-level* nil
            *print-dup* false
            *print-meta* false
            *print-readably* true

            ;; namespaced maps not part of edn spec
            *print-namespace-maps* false

            *out* w]
    (pr obj)))

(defn dump-api [opts]
  (let [outf (io/file
              "resources"
              "fastleannative"
              "small3d"
              "api.edn")]
    (.mkdirs (.getParentFile outf))
    (with-open [w (io/writer outf)]
      (write-edn w
                 ((requiring-resolve 'com.phronemophobic.clong.clang/easy-api)
                  (.getCanonicalPath (io/file "c" 
                                              "third_party"
                                              "terminalCube.h")))))))

(defn load-api []
  (with-open [rdr (io/reader
                   (io/resource
                    "fastleannative/small3d/api.edn"))
              rdr (java.io.PushbackReader. rdr)]
    (edn/read rdr)))
