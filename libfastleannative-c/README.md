
# libfastleannative-c

Write a babashka script to call a simple native function.

## Exercise

1. Compile the c code into a shared library

_Note: gcc can be substituted for clang_

Mac OSX:
```sh
clang -c fastleannative.c -o fastleannative.o
# link into a shared library
clang -dynamiclib fastleannative.o -o libfastleannative.dylib 
```

Linux:
```sh
clang -fPIC -c fastleannative.c -o fastleannative.o
# link into a shared library
clang -shared fastleannative.o -o libfastleannative.so 
```

2. Edit the babashka script, `fastleannative.bb` to call `fastleannative_add`

### Bonus

Add more functions to `fastleannative.c` and call them from `fastleannative.bb`. Try using different types beside integers. Remember to recompile the c code into the native library after changing `fastleannative.c`.


