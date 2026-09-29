# small3d

Wrap the single header library, [small3dlib](https://gitlab.com/drummyfish/small3dlib/) to produce to render a rotating 3d cube using ascii.

## Goals

- Wrap a non trivial native library
- Manipulate off-heap data

## Setup

Requires gcc or clang.

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


