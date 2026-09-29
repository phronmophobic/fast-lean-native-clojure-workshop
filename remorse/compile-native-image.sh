#!/bin/bash

set -e
set -x

native-image \
    -jar ./target/remorse-0.1.0-standalone.jar \
    -o ./target/remorse \
    -H:+ReportExceptionStackTraces \
    -J-Dclojure.compiler.direct-linking=true \
    --features=clj_easy.graal_build_time.InitClojureClasses \
    --no-fallback 

