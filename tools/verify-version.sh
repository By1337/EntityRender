#!/bin/sh
# Full local validation; does not change Git branches or commit.
set -eu
cd "$(dirname "$0")/.."
server_dir="$(cd "$1" && pwd)"
target_version="$(sed -n 's:.*<minecraft.version>\([^<]*\)</minecraft.version>.*:\1:p' pom.xml)"
java_release="$(sed -n 's:.*<maven.compiler.release>\([^<]*\)</maven.compiler.release>.*:\1:p' pom.xml)"
server_java="$(sed -n 's:.*<minecraft.java.version>\([^<]*\)</minecraft.java.version>.*:\1:p' pom.xml)"
export JAVA_HOME="/usr/lib/jvm/java-${server_java:-$java_release}-openjdk"
bundle="$(python3 tools/server_libraries.py --bundle "$server_dir")"
mvn -o -q clean package
sh test.sh "$server_dir/versions/$target_version/paper-$target_version.jar" --bundle "$bundle" --libraries "$server_dir/libraries" --java "$JAVA_HOME/bin/java"
sh test-paper.sh "$server_dir"
mkdir -p "dist/$target_version"
cp "api/target/entity-render-api-2.0.0+$target_version-SNAPSHOT.jar" "core/target/entity-render-core-2.0.0+$target_version-SNAPSHOT.jar" "dist/$target_version/"
cp dumper/target/entity-dumper.jar "dist/$target_version/"
