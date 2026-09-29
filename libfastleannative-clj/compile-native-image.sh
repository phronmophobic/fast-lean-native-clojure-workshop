#!/bin/bash

set -e
set -x

native-image \
    -jar ./target/libfastleannative-0.1.0-standalone.jar \
    -o ./target/libfastleannative \
    -H:+ReportExceptionStackTraces \
    -J-Dclojure.compiler.direct-linking=true \
    -J-Dclojure.spec.skip-macros=true \
    -J-Dtech.v3.datatype.graal-native=true \
    --shared \
    --features=clj_easy.graal_build_time.InitClojureClasses \
    --no-fallback 

