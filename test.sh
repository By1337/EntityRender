#!/bin/sh
set -eu
cd "$(dirname "$0")"
if [ "$#" -lt 1 ]; then
  echo 'Usage: sh test.sh <remapped-1.21.4.jar> [--bundle original.jar | --libraries directory] [--java java]'
  exit 1
fi
minecraft="$1"
shift
sh build.sh
test_dir="$(mktemp -d "${TMPDIR:-/tmp}/entityrender-test-XXXXXX")"
java_bin="${JAVA_HOME:+$JAVA_HOME/bin/}java"
javac_bin="${JAVA_HOME:+$JAVA_HOME/bin/}javac"
"$java_bin" -jar dumper/target/entity-dumper.jar dump --minecraft "$minecraft" --out "$test_dir/first" "$@"
"$java_bin" -jar dumper/target/entity-dumper.jar dump --minecraft "$minecraft" --out "$test_dir/second" "$@"
"$java_bin" -jar dumper/target/entity-dumper.jar verify --expected "$test_dir/first" --actual "$test_dir/second"
"$java_bin" -jar dumper/target/entity-dumper.jar verify --expected dumper/src/test/resources/golden/1.21.4 --actual "$test_dir/first"
"$java_bin" -jar dumper/target/entity-dumper.jar generate --schema "$test_dir/first" --out "$test_dir/generated"
# Structural API/core skeletons are JDK-only; typed GeneratedMetadata is compiled
# with NMS and the metadata runtime by build-runtime.sh / Maven.
find "$test_dir/generated" -name '*.java' ! -name GeneratedMetadata.java | LC_ALL=C sort > "$test_dir/generated-sources.txt"
"$javac_bin" --release 21 -d "$test_dir/generated-classes" @"$test_dir/generated-sources.txt"
mkdir -p dumper/target/test-classes
find dumper/target/test-classes -type f -name '*.class' -delete
find dumper/src/test/java -name '*.java' | LC_ALL=C sort > dumper/target/test-sources.txt
"$javac_bin" --release 21 -cp dumper/target/classes -d dumper/target/test-classes @dumper/target/test-sources.txt
"$java_bin" -cp dumper/target/classes:dumper/target/test-classes dev.by1337.entityrender.dumper.DumperChecks "$test_dir/first"
"$java_bin" -cp dumper/target/classes:dumper/target/test-classes dev.by1337.entityrender.dumper.GeneratorChecks
echo "Integration checks passed; artifacts: $test_dir"
