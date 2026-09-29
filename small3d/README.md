# small3d

Wrap the single header library, [small3dlib](https://gitlab.com/drummyfish/small3dlib/) to produce to render a rotating 3d cube using ascii.

## Goals

- Wrap a non trivial native library
- Manipulate off-heap data

## Setup

Requires gcc or clang.

## Dtype next primer

Dtype-next provides utilities for working with off-heap memory.

```clojure
(require '[tech.v3.datatype.struct :as dt-struct])
(import 'java.util.Map)

(dt-struct/define-datatype! :vec3
                            [{:name :x :datatype :float32}
                             {:name :y :datatype :float32}
                             {:name :z :datatype :float32}])

(def myvec
  (dt-struct/map->struct :vec3
                       {:x 1
                        :y 2
                        :z 3}))
;; set values using java.util.Map/.put
;; equivalent to the following c code
;; myvec.x = 42;
(java.util.Map/.put myvec :x 42)
(:x myvec) ;; => 42.0


;; Structs can be nested
(dt-struct/define-datatype! :twovec
                            [{:name :v1 :datatype :vec3}
                             {:name :v2 :datatype :vec3}])

(def mytwovec 
  (dt-struct/map->struct 
   :twovec
   {:v1 (dt-struct/map->struct :vec3
                               {:x 1
                                :y 2
                                :z 3})
    :v2 (dt-struct/map->struct :vec3
                               {:x 1
                                :y 2
                                :z 3})}))

(import 'java.util.Map)

;; You can update values in nested structs
;; equivalent to the following c code
;; mytwovec.v1.x = 42
(Map/.put (:v1 mytwovec) :x 42)

mytwovec
;; {:v1 {:x 42.0, :y 2.0, :z 3.0},
;;  :v2 {:x 1.0, :y 2.0, :z 3.0}}

```

### Compile libterminalcube
```sh
cd small3d/c/third_party

# for clang
CC=clang ./compile.sh

# or for gcc
# CC=gcc ./compile.sh
```

### Exercise

_Note: make sure the `:project` alias is used or somehow add the following jvm opts_
```
-Djna.library.path=c/third_party
--enable-native-access=ALL-UNNAMED
```

1. Port the commented c code in `-main` found in `src/fastleannative/small3d.clj` to clojure.
2. Test the program with `clojure -M:project -m fastleannative.small3d
3. Edit the script to change the rotation of the cube.
4. Edit the script to change the translate of the cube.

## Bonus

1. Use a terminal UI library to add keyboard controls
2. Regenerate the api data using `clojure -X:dump-api`
3. Inspect the api data in `fastleannative.small3d/api`.
4. Replace dtype-next ffi with [babashka.ffi](https://github.com/babashka/ffi)
5. **Advanced**: Use the small3d template to port one of the other examples from `c/third_party/small3dlib/programs/`


## License

The contents of c/third_party are from https://gitlab.com/drummyfish/small3dlib/.

For everything else.

The MIT License (MIT)

Copyright © 2026 Adrian Smith

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


