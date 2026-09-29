# libfastleannative

Build a shared library from clojure code using native-image.

## Exercise

1. Build the uber jar `clojure -T:build uber`
2. Compile the shared library using native image `./compile-native-image.sh`
3. Compile the test c program

_Note: gcc can be substituted for clang_

```sh
cd c
CC=clang ./compile.sh
```

4. Run the test program
```sh
cd c
./main
```
5. Inspect `main.c`, and `target/libfastleannative.h`.
6. Add a `clj_sub` function to `src/fastleannative/libfastleannative.clj` which subtracts two integers 
7. Update `main.c` to call `clj_sub`.

## Bonus

1. Add more functions to your shared library. 
2. Use different data types besides ints.
3. Compare the memory usage, file size, and run time of the main executable with an equivalent clojure program.

## Resources

https://www.graalvm.org/latest/reference-manual/native-image/guides/build-native-shared-library/


## License

The MIT License (MIT)

Copyright © 2026 Adrian Smith

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


