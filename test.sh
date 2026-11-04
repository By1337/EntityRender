#!/bin/sh
set -eu
cd "$(dirname "$0")"
if [ "$#" -lt 1 ]; then
  echo 'Usage: sh test.sh <mojang-named-server.jar> [--bundle original.jar | --libraries directory] [--java java]'
  exit 1
fi
target_version="$(sed -n 's:.*<minecraft.version>\([^<]*\)</minecraft.version>.*:\1:p' pom.xml)"
minecraft="$1"
shift
sh build.sh
test_dir="$(mktemp -d "${TMPDIR:-/tmp}/entityrender-test-XXXXXX")"
java_bin="${JAVA_HOME:+$JAVA_HOME/bin/}java"
javac_bin="${JAVA_HOME:+$JAVA_HOME/bin/}javac"
"$java_bin" -jar dumper/target/entity-dumper.jar dump --minecraft "$minecraft" --out "$test_dir/first" "$@"
"$java_bin" -jar dumper/target/entity-dumper.jar dump --minecraft "$minecraft" --out "$test_dir/second" "$@"
"$java_bin" -jar dumper/target/entity-dumper.jar verify --expected "$test_dir/first" --actual "$test_dir/second"
"$java_bin" -jar dumper/target/entity-dumper.jar verify --expected "dumper/src/test/resources/golden/$target_version" --actual "$test_dir/first"
"$java_bin" -jar dumper/target/entity-dumper.jar generate --schema "$test_dir/first" --out "$test_dir/generated"
# Structural API/core skeletons are JDK-only; typed GeneratedMetadata is compiled
# with NMS and the metadata runtime by Maven.
find "$test_dir/generated" -name '*.java' ! -name GeneratedMetadata.java | LC_ALL=C sort > "$test_dir/generated-sources.txt"
"$javac_bin" --release 21 -d "$test_dir/generated-classes" @"$test_dir/generated-sources.txt"
mkdir -p dumper/target/test-classes
find dumper/target/test-classes -type f -name '*.class' -delete
find dumper/src/test/java -name '*.java' | LC_ALL=C sort > dumper/target/test-sources.txt
"$javac_bin" --release 21 -cp dumper/target/classes -d dumper/target/test-classes @dumper/target/test-sources.txt
"$java_bin" -cp dumper/target/classes:dumper/target/test-classes dev.by1337.entityrender.dumper.DumperChecks "$test_dir/first"
"$java_bin" -cp dumper/target/classes:dumper/target/test-classes dev.by1337.entityrender.dumper.GeneratorChecks
"$java_bin" -cp dumper/target/classes:dumper/target/test-classes dev.by1337.entityrender.dumper.CaptureLauncherChecks
echo "Integration checks passed; artifacts: $test_dir"
