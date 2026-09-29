(ns fastleannative.small3d
  (:require [fastleannative.small3d.gen-api :as gen-api]
            [tech.v3.datatype.struct :as dt-struct]
            [tech.v3.datatype.ffi :as dt-ffi]
            [com.phronemophobic.clong.gen.dtype-next :as gen]
            [tech.v3.datatype.native-buffer :as native-buffer])
  (:import [tech.v3.datatype.ffi Pointer]
           java.util.Map))


(def libterminalcube
  (com.sun.jna.NativeLibrary/getInstance 
   "terminalcube"))

(def api (gen-api/load-api))

(def dtype-api (gen/api->library-interface api))
(def dtype-structs (gen/api->structs api))
(doseq [[id fields] dtype-structs]
  (dt-struct/define-datatype! id fields))

(dt-ffi/define-library-interface dtype-api)

(def pixel-callback-iface (dt-ffi/define-foreign-interface :void [:pointer]))

(def screen (atom {}))
(defn pixel-callback [pixel-info]
  (let [{:keys [x y triangleIndex]} pixel-info
        c (case triangleIndex
            (0 1 4 5) "#"
            (2 3 6 7) "x"
            ;; else
            ".")]
   (swap! screen assoc [x y] c)))

(def screen-width 80)
(def screen-height 40)

(defn clear-screen []
 (print (str \u001b "[2J" \u001b "[H"))
 (flush))

(defn print-screen [screen]
  (doseq [j (range screen-height)]
    (doseq [i (range screen-width)]
      (print (get screen [i j] " ")))
    (println)))

(def pixel-callback-iface-inst
  (dt-ffi/instantiate-foreign-interface
   pixel-callback-iface
   (fn [^Pointer pixel-info-p]
     (let [pixel-info (dt-ffi/ptr->struct :S3L_PixelInfo pixel-info-p)]
       (pixel-callback pixel-info)))))
(def pixel-callback-iface-ptr (dt-ffi/foreign-interface-instance->c pixel-callback-iface pixel-callback-iface-inst))

(setPixelCallback pixel-callback-iface-ptr )

(def cubeVertices (com.sun.jna.NativeLibrary/.getGlobalVariableAddress libterminalcube  "cubeVertices"))
(def cubeTriangles (com.sun.jna.NativeLibrary/.getGlobalVariableAddress libterminalcube  "cubeTriangles"))

(def S3L_CUBE_VERTEX_COUNT 8)
(def S3L_CUBE_TRIANGLE_COUNT 12)
(def S3L_FRACTIONS_PER_UNIT 512)
(def S3L_F S3L_FRACTIONS_PER_UNIT)

(def cube-model (dt-struct/new-struct :S3L_Model3D
                                      {:container-type :native-heap}))
(def scene (dt-struct/new-struct :S3L_Scene
                                 {:container-type :native-heap}))


(defn -main [& args]

  (S3L_model3DInit cubeVertices
                   S3L_CUBE_VERTEX_COUNT
                   cubeTriangles
                   S3L_CUBE_TRIANGLE_COUNT
                   cube-model)

  (S3L_sceneInit cube-model
                 1
                 scene)
  
  (let [translation (-> scene :camera :transform :translation)]
    (Map/.put translation :z (* -2 S3L_F)))

  (let [num-frames (if-let [num-str (first args)]
                     (parse-long num-str)
                     200)]
    (dotimes [i num-frames]
      (clear-screen)
      (reset! screen {})
      
      (S3L_newFrame)
      (S3L_drawScene scene)
      
      (print-screen @screen)
      (let [models (:models scene)
            model (dt-ffi/ptr->struct :S3L_Model3D models)
            transform (-> model :transform)
            translation (-> transform :translation)
            rotation (-> transform :rotation)]

        (Map/.put rotation :y (+ 10 (:y rotation)))
        (Map/.put rotation :x (+ 4 (:x rotation)))

        (Map/.put translation :x (S3L_sin (* i 4)))
        (Map/.put translation :y (/ (S3L_sin (* i 2)) 2)))
      
      (Thread/sleep 100))))

