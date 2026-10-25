#!/bin/sh
set -eu
cd "$(dirname "$0")"
if [ "$#" -ne 1 ]; then echo 'Usage: sh generate-runtime.sh <schema-directory>'; exit 1; fi
sh build.sh
work_dir="$(mktemp -d "${TMPDIR:-/tmp}/entityrender-runtime-XXXXXX")"
trap 'rm -rf -- "$work_dir"' EXIT HUP INT TERM
"${JAVA_HOME:+$JAVA_HOME/bin/}java" -jar dumper/target/entity-dumper.jar runtime --schema "$1" --out "$work_dir/generated"
(
  cd "$work_dir/generated"
  find api core -name '*.java' | sed 's|^api/|api/src/main/java/|;s|^core/|core/src/main/java/|' | LC_ALL=C sort
) > "$work_dir/files"
if [ -f .generated-runtime-files ]; then
  comm -23 .generated-runtime-files "$work_dir/files" > "$work_dir/stale"
  while IFS= read -r file; do
    case "$file" in api/src/main/java/dev/by1337/entityrender/api/*.java|core/src/main/java/dev/by1337/entityrender/core/Generated*.java|core/src/main/java/dev/by1337/entityrender/core/TargetVersion.java|core/src/main/java/dev/by1337/entityrender/core/generated/Generated*.java|core/src/main/java/dev/by1337/entityrender/core/generated/TargetVersion.java) rm -f -- "$file" ;; *) echo "Invalid generated path: $file"; exit 1 ;; esac
  done < "$work_dir/stale"
fi
cp -a "$work_dir/generated/api/." api/src/main/java/
cp -a "$work_dir/generated/core/." core/src/main/java/
cp "$work_dir/files" .generated-runtime-files
xargs -d '\n' java-format -- < .generated-runtime-files
