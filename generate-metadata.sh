#!/bin/sh
set -eu
cd "$(dirname "$0")"
if [ "$#" -ne 1 ]; then
  echo 'Usage: sh generate-metadata.sh <schema-directory>'
  exit 1
fi
sh build.sh
work_dir="$(mktemp -d "${TMPDIR:-/tmp}/entityrender-metadata-XXXXXX")"
trap 'rm -rf -- "$work_dir"' EXIT HUP INT TERM
"${JAVA_HOME:+$JAVA_HOME/bin/}java" -jar dumper/target/entity-dumper.jar generate \
  --schema "$1" --defaults core/src/main/metadata/defaults.tsv --out "$work_dir/generated"
target=core/src/main/java/dev/by1337/entityrender/core/metadata/GeneratedMetadata.java
cp "$work_dir/generated/core/dev/by1337/entityrender/core/metadata/GeneratedMetadata.java" "$target"
java-format -- "$target"
