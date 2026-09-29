(ns fastleannative.boom
  (:require [babashka.ffi :as ffi :refer [defcfn]]))


(defn -main [& args]
  (let [strlen (ffi/cfn "strlen" [:long] :void )]
    @(future (strlen (rand-int 10000)))))

