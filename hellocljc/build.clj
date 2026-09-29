(ns build
  (:require [clojure.tools.build.api :as b]))

(def lib 'fastleannative/hellocljc)
(def version "0.1.0")
(def class-dir "target/classes")
(def jar-file (format "target/%s-%s.jar" (name lib) version))
(def uber-file (format "target/%s-%s-standalone.jar" (name lib) version))

(defn clean [_]
  (b/delete {:path "target"}))

(defn uber [_]
  (let [basis (b/create-basis {:project "deps.edn"
                               :aliases [:native-image]})]
    (clean nil)
    (b/copy-dir {:src-dirs ["src" "resources"]
                 :target-dir class-dir})
    (b/compile-clj {:basis basis
                    :ns-compile '[fastleannative.hellocljc]
                    :class-dir class-dir})
    (b/uber {:class-dir class-dir
             :uber-file uber-file
             :basis basis
             :main 'fastleannative.hellocljc})))



