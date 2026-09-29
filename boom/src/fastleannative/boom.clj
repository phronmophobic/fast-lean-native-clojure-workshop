(ns fastleannative.boom
  (:require [babashka.ffi :as ffi :refer [defcfn]]))

(defn -main [& args]
  (let [strlen (ffi/cfn "strlen" [:pointer] :size_t )]
    (with-open [arena (ffi/confined-arena)]
      (let [p (ffi/string->ptr arena "hello world")]
        (prn (strlen p ))))))

