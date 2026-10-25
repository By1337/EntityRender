#!/bin/sh
set -eu
cd "$(dirname "$0")"
if [ "$#" -ne 2 ]; then
  echo 'Usage: JAVA_HOME=/path/to/jdk-21 sh test-runtime.sh /path/to/prepared-paper-1.21.4-server /path/to/unbundled-vanilla-1.21.4.jar'
  exit 1
fi
sh build-runtime.sh "$1"
mkdir -p core/target/test-classes
find core/target/test-classes -type f -name '*.class' -delete
find core/src/test/java -name '*.java' | LC_ALL=C sort > core/target/test-sources.txt
classpath="api/target/classes:core/target/classes:$(cat core/target/server-classpath.txt)"
"${JAVA_HOME:+$JAVA_HOME/bin/}javac" --release 21 -encoding UTF-8 -cp "$classpath" -d core/target/test-classes @core/target/test-sources.txt
# Vanilla first: bootstrap without a running CraftServer. Craft classes are only used
# by the live Paper transport / conversion paths, not by this packet-capture harness.
"${JAVA_HOME:+$JAVA_HOME/bin/}java" -cp "$2:core/target/test-classes:$classpath" dev.by1337.entityrender.core.RuntimeChecks
