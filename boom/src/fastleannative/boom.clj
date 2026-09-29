(ns fastleannative.boom
  (:require [babashka.ffi :as ffi :refer [defcfn]]))






(def zlib (ffi/load-system-library "z"))
(def zlib-version (ffi/cfn zlib "zlibVersion" [:int :long] :void ))


(defn -main [& args]
  (let [strlen (ffi/cfn "strlen" [:long] :void )]
    @(future (strlen (rand-int 10000))))
  )


;; (def libfastleannative (ffi/load-library "../libfastleannative/libfastleannative.dylib"))
;; (def libfastleannative_add (ffi/cfn zlib "fastleannative_add" [:int :int] :int ))

;; (libfastleannative_add 1 2)
