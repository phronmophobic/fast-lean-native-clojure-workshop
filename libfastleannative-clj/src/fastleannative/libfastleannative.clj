(ns fastleannative.libfastleannative
  (:gen-class))


(defn clj_add [a b]
  (+ a b))

(defn compile-interface-class [& args]
  ((requiring-resolve 'tech.v3.datatype.ffi.graalvm/expose-clojure-functions)
   {#'clj_add {:rettype :int32
               :argtypes [['a :int32]
                          ['b :int32]]}}

   'fastleannative.libfastleannative.interface nil))

(when *compile-files*
  (compile-interface-class))

(defn -main [& args])
