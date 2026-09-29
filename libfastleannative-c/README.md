
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

Add more functions to `fastleannative.c` and call them from `fastleannative.bb`. Try using different types beside integers.


## License

The MIT License (MIT)

Copyright © 2026 Adrian Smith

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


