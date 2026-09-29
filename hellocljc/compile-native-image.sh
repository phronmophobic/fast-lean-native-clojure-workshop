#!/bin/bash

set -e
set -x

native-image \
    -jar ./target/hellocljc-0.1.0-standalone.jar \
    -o ./target/hello-world-native-image \
    -H:+ReportExceptionStackTraces \
    -J-Dclojure.compiler.direct-linking=true \
    --features=clj_easy.graal_build_time.InitClojureClasses \
    --no-fallback 

