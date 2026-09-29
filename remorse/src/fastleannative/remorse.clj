(ns fastleannative.remorse
  (:require [remorse.core :as rm]
            clojure.walk
            [clojure.java.io :as io]
            [clojure.edn :as edn])
  (:import java.io.PushbackReader)
  (:gen-class))


(defn -main [& args]
  (let [fname (or (first args)
                  "deps.edn")
        eof (Object.)]
    
    (with-open [rdr (io/reader fname)
                pbr (PushbackReader. rdr 1)
                w (io/writer "out.edn")]
      (binding [*out* w]
        (loop []
          (let [form (edn/read {:eof eof} pbr)]
            (when (not (identical? form eof))
              (let [form (clojure.walk/postwalk
                          (fn [o]
                            (cond
                              (string? o) (rm/string->morse o)
                              (keyword? o) (rm/keyword->morse o)
                              (symbol? o) (rm/symbol->morse o)
                              :else o))
                          form)]
                (pr form)
                (prn))
              (recur))))))))



