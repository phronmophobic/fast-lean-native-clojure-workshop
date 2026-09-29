(ns fastleannative
  (:require [babashka.ffi :as ffi :refer [defcfn]]
            [babashka.fs :as fs]))


(ffi/load-system-library "fastleannative")

(defcfn fastleannative-add "fastleannative_add" [:int :int] :int)

(prn (fastleannative-add 1 2))

