

#!/bin/bash

set -e
set -x

case "$(uname)" in
  Darwin) SUFFIX=".dylib"; OS_ARGS=""; SHARED="-dynamiclib";  ;;
  *)      SUFFIX=".so"; OS_ARGS="-fPIC"; SHARED="-shared" ;;
esac

"$CC" "$OS_ARGS" -c terminalCube.c -o terminalCube.o
"$CC" "$SHARED" -o libterminalcube"$SUFFIX" terminalCube.o
