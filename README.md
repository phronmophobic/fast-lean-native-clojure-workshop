# Fast, Lean, Native Clojure Workshop

Working with native libraries and off-heap memory has historically been awkward and cumbersome. With the advent of tools like Project Panama and GraalVM's Native Image, Clojure developers now have powerful tools for working within and alongside native libraries written in c, c++, rust, and more. Using a combination of instruction and hands-on learning, this workshop will guide participants in the best practices of leveraging the native ecosystem.

This session is designed for developers familiar with Clojure.

## Main Goals
- improve startup time
- lower cpu and memory usage
- create standalone executables
- call native libraries
- manipulate off-heap memory
- create native libraries using clojure code

## Setup

### Linux

```sh
sudo apt-get install clang binutils
# graalvm deps
sudo apt-get install build-essential zlib1g-dev
```

### Mac OSX

Install homebrew: https://brew.sh/

Install clang:
```sh
brew install llvm
```

### All systems

- Install native-image
  - https://www.graalvm.org/downloads/
  - https://www.graalvm.org/latest/getting-started/
    - For all exercises, make sure `$JAVA_HOME` is set to the graalvm java and that `native-image` is on the `$PATH`
- Install babashka - https://github.com/babashka/babashka#installation
- Make sure to initialize and update the repository submodules `git submodule update --init`


## Exercises

- [boom](https://github.com/phronmophobic/fast-lean-native-clojure-workshop/tree/main/boom) - Crash the JVM!
- [hellocljc](https://github.com/phronmophobic/fast-lean-native-clojure-workshop/tree/main/hellocljc) - Create a hello world native image standalone executable and measure performance
- [remorse](https://github.com/phronmophobic/fast-lean-native-clojure-workshop/tree/main/remorse) - Create a native image standalone executable for non trivial program and measure performance.
- [libfastleannative-c](https://github.com/phronmophobic/fast-lean-native-clojure-workshop/tree/main/libfastleannative-c) - Write a babashka script to call a simple native function.
- [libfastleannative-clj](https://github.com/phronmophobic/fast-lean-native-clojure-workshop/tree/main/libfastleannative-clj) - Build a shared library from clojure code using native-image.

- [small3d](https://github.com/phronmophobic/fast-lean-native-clojure-workshop/tree/main/small3d) - Wrap the single header library, [small3dlib](https://gitlab.com/drummyfish/small3dlib/) to render a rotating 3d cube using ascii.


## Resources

- [Supplemental material](https://blog.phronemophobic.com/fastleannative.html): Contains a review of many of the topics covered during the workshop as well as more in-depth info about related topics.
- [dtype-next overview](https://cnuernber.github.io/dtype-next/overview.html)
- [babashka.ffi](https://github.com/babashka/ffi)
- [https://jank-lang.org/](jank)
- [Comparison of ffi options for clojure](https://docs.google.com/spreadsheets/u/1/d/e/2PACX-1vQAiX80h3wsbwo7qv8aAOp2TFLO6V2dJV5Ay24xihhKObDhT7HwS0nbZGUPxLjaJc9rSwoN-tNksFda/pubhtml#gid=1519410866)
- https://www.graalvm.org/latest/reference-manual/native-image/guides/build-native-shared-library/
- clojure specific graalvm native resources: https://github.com/clj-easy/graal-docs, https://github.com/BrunoBonacci/graalvm-clojure
- #graalvm on the clojurians slack

## License

The MIT License (MIT)

Copyright © 2026 Adrian Smith

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


