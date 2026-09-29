# hellocljc

Create a hello world native image standalone executable and measure performance.

## Exercise

Goal: Make sure your computer is setup to use `native-image`. Compare the size and speed of a hello world program running on the JVM vs as a native image.

### Steps

1. Create an uberjar `clojure -T:build uber`
_Note: make sure `$JAVA_HOME` is set to the graalvm java when creating the uberjar._
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

