#!/bin/bash

set -e
set -x

RPATH_LINUX='-Wl,-rpath,$ORIGIN/../target'
RPATH_MAC='-Wl,-rpath,@executable_path/../target'

case "$(uname)" in
  Darwin) RPATH="$RPATH_MAC" ;;
  *)      RPATH="$RPATH_LINUX" ;;
esac

"$CC" -I ../target -L ../target main.c -lfastleannative "$RPATH" -o main
