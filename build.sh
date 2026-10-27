#!/bin/sh
set -eu
cd "$(dirname "$0")"
mkdir -p dumper/target/classes
find dumper/target/classes -type f -name '*.class' -delete
find dumper/src/main/java -name '*.java' | LC_ALL=C sort > dumper/target/sources.txt
"${JAVA_HOME:+$JAVA_HOME/bin/}javac" --release 21 -encoding UTF-8 -d dumper/target/classes @dumper/target/sources.txt
cp -R dumper/src/main/resources/. dumper/target/classes/
"${JAVA_HOME:+$JAVA_HOME/bin/}jar" --create --file dumper/target/entity-dumper.jar --main-class dev.by1337.entityrender.dumper.Main -C dumper/target/classes .
