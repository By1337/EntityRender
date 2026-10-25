#!/bin/sh
set -eu
cd "$(dirname "$0")"
if [ "$#" -ne 1 ]; then
  echo 'Usage: JAVA_HOME=/path/to/jdk-21 sh build-runtime.sh /path/to/prepared-paper-1.21.4-server'
  exit 1
fi
server_dir="$(cd "$1" && pwd)"
mkdir -p api/target/classes core/target/classes
find api/target/classes core/target/classes -type f -name '*.class' -delete
find "$server_dir/libraries" -name '*.jar' | LC_ALL=C sort > core/target/server-jars.txt
if [ ! -f "$server_dir/versions/1.21.4/paper-1.21.4.jar" ]; then
  echo 'Prepared Paper 1.21.4 server jar not found'
  exit 1
fi
classpath="$server_dir/versions/1.21.4/paper-1.21.4.jar:$(paste -sd : core/target/server-jars.txt)"
find api/src/main/java -name '*.java' | LC_ALL=C sort > api/target/sources.txt
find core/src/main/java -name '*.java' | LC_ALL=C sort > core/target/sources.txt
"${JAVA_HOME:+$JAVA_HOME/bin/}javac" --release 21 -encoding UTF-8 -cp "$classpath" -d api/target/classes @api/target/sources.txt
"${JAVA_HOME:+$JAVA_HOME/bin/}javac" --release 21 -encoding UTF-8 -cp "api/target/classes:$classpath" -d core/target/classes @core/target/sources.txt
"${JAVA_HOME:+$JAVA_HOME/bin/}jar" --create --file api/target/entity-render-api.jar -C api/target/classes .
printf 'Manifest-Version: 1.0\npaperweight-mappings-namespace: mojang\n\n' > core/target/MANIFEST.MF
"${JAVA_HOME:+$JAVA_HOME/bin/}jar" --create --file core/target/entity-render-core.jar --manifest core/target/MANIFEST.MF -C core/target/classes .
printf '%s\n' "$classpath" > core/target/server-classpath.txt
