#!/bin/sh
# Full local validation; does not change Git branches or commit.
set -eu
cd "$(dirname "$0")/.."
server_dir="$(cd "$1" && pwd)"
target_version="$(sed -n 's:.*<minecraft.version>\([^<]*\)</minecraft.version>.*:\1:p' pom.xml)"
java_release="$(sed -n 's:.*<maven.compiler.release>\([^<]*\)</maven.compiler.release>.*:\1:p' pom.xml)"
export JAVA_HOME="/usr/lib/jvm/java-$java_release-openjdk"
bundle="$(python3 - "$server_dir" <<'PY'
from pathlib import Path
import sys
bundles = list(Path(sys.argv[1]).glob('paper*.jar'))
if len(bundles) != 1:
    raise SystemExit('Expected exactly one Paper bootstrap jar')
print(bundles[0])
PY
)"
mvn -o -q clean package
sh test.sh "$server_dir/versions/$target_version/paper-$target_version.jar" --bundle "$bundle" --libraries "$server_dir/libraries" --java "$JAVA_HOME/bin/java"
sh test-paper.sh "$server_dir"
mkdir -p "dist/$target_version"
cp "api/target/entity-render-api-2.0.0+$target_version-SNAPSHOT.jar" "core/target/entity-render-core-2.0.0+$target_version-SNAPSHOT.jar" "dist/$target_version/"
cp dumper/target/entity-dumper.jar "dist/$target_version/"
