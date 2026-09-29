# hellocljc

Create a hello world native image standalone executable and measure performance.

## Exercise

Goal: Make sure your computer is setup to use `native-image`. Compare the size and speed of a hello world program running on the JVM vs as a native image.

### Steps

1. Create an uberjar `clojure -T:build uber`
2. Compile the native image `./compile-native-image.sh`
3. Run the native image `./target/hello-world-native-image`
4. Compare the sizes

```sh
ls -lh target/hellocljc-0.1.0-standalone.jar
ls -lh ./target/hello-world-native-image
```
5. Compare the memory usage (Maximum resident set size) and run time of the hello world program on the JVM vs native image

_Note_: `/usr/bin/time` must be used rather than `time` on both linux and mac osx.

Linux:
```sh
/usr/bin/time -v java -jar target/hellocljc-0.1.0-standalone.jar
/usr/bin/time -v ./target/hello-world-native-image
```

Mac OSX:
```sh
/usr/bin/time -l java -jar target/hellocljc-0.1.0-standalone.jar
/usr/bin/time -l ./target/hello-world-native-image
```

## License

The MIT License (MIT)

Copyright © 2026 Adrian Smith

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


