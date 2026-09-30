# libfastleannative

Build a shared library from clojure code using native-image.

## Exercise


1. Build the uber jar `clojure -T:build uber`
_Note: make sure `$JAVA_HOME` is set to the graalvm java when creating the uberjar._
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
7. Recompile the native image.
8. Update `main.c` to call `clj_sub`.
9. Recompile the c file and rerun the test program.
10. Inspect the symbols of the created shared library

Linux: `nm -g target/libfastleannative.so`
Mac OSX: `nm -g target/libfastleannative.dylib`
11. Inspect the dependencies of the shared library

Linux: `ldd target/libfastleannative.so`
Mac OSX: `otool -L target/libfastleannative.so`

## Bonus

1. Add more functions to your shared library. 
2. Use different data types besides ints.
3. Compare the memory usage, file size, and run time of the main executable with an equivalent clojure program.

## Resources

https://www.graalvm.org/latest/reference-manual/native-image/guides/build-native-shared-library/


