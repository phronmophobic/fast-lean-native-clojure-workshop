# remorse

Create a native image standalone executable for non trivial program and measure performance.

## Exercise

Goal: Make sure your computer is setup to use `native-image`. Compare the size and speed of the program.

### Steps

1. Create an uberjar `clojure -T:build uber`
2. Compile the native image `./compile-native-image.sh`
3. Run the native image `./target/remorse`
4. Compare the sizes

```sh
ls -lh target/remorse-0.1.0-standalone.jar
ls -lh ./target/remorse
```
5. Compare the memory usage (Maximum resident set size) and run time of the hello world program on the JVM vs native image

_Note_: `/usr/bin/time` must be used rather than `time` on both linux and mac osx.

Linux:
```sh
/usr/bin/time -v java -jar target/remorse-0.1.0-standalone.jar
/usr/bin/time -v ./target/remorse
```

Mac OSX:
```sh
/usr/bin/time -l java -jar target/remorse-0.1.0-standalone.jar
/usr/bin/time -l ./target/remorse
```

### Bonus

Extend the program to see how it affects the size, memory usage, and run time of the program.

