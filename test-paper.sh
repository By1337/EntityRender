#!/bin/sh
set -eu
cd "$(dirname "$0")"
if [ "$#" -ne 1 ]; then
  echo 'Usage: JAVA_HOME=/path/to/jdk-21 sh test-paper.sh /path/to/prepared-paper-1.21.4-server'
  exit 1
fi
sh build-runtime.sh "$1"
java_release="$(cat core/target/java-release.txt)"
target_version="$(cat core/target/minecraft-version.txt)"
mkdir -p core/target/test-classes
find core/target/test-classes -type f -name '*.class' -delete
find core/src/test/java -name '*.java' | LC_ALL=C sort > core/target/test-sources.txt
classpath="$(cat core/target/server-classpath.txt)"
"${JAVA_HOME:+$JAVA_HOME/bin/}javac" --release "$java_release" -encoding UTF-8 -cp "api/target/classes:core/target/classes:$classpath" -d core/target/test-classes @core/target/test-sources.txt
test_dir="$(mktemp -d "${TMPDIR:-/tmp}/entityrender-paper-XXXXXX")"
mkdir -p "$test_dir/plugins" "$test_dir/plugin-meta"
cat > "$test_dir/plugin-meta/plugin.yml" <<EOF
name: EntityRenderIntegrationTest
version: '1.0'
main: dev.by1337.entityrender.core.PaperIntegrationPlugin
api-version: '$target_version'
EOF
"${JAVA_HOME:+$JAVA_HOME/bin/}jar" --create --file "$test_dir/plugins/entityrender-test.jar" --manifest core/target/MANIFEST.MF -C api/target/classes . -C core/target/classes . -C core/target/test-classes . -C "$test_dir/plugin-meta" plugin.yml
cat > "$test_dir/server.properties" <<'EOF'
server-ip=127.0.0.1
server-port=0
online-mode=false
enforce-secure-profile=false
enable-status=false
spawn-protection=0
view-distance=2
simulation-distance=2
level-type=minecraft:flat
generator-settings={"layers":[{"block":"minecraft:bedrock","height":1}],"biome":"minecraft:plains"}
generate-structures=false
allow-nether=false
level-seed=1
pause-when-empty-seconds=-1
EOF
printf 'eula=true\n' > "$test_dir/eula.txt"
java_bin="${JAVA_HOME:+$JAVA_HOME/bin/}java"
case "$java_bin" in /*) ;; *) java_bin="$(command -v "$java_bin")" ;; esac
echo "Starting isolated Paper integration server in $test_dir"
(
  cd "$test_dir"
  timeout 120 "$java_bin" -Dterminal.jline=false -Dterminal.ansi=false -XX:ActiveProcessorCount=2 -Xms512m -Xmx1536m -cp "$classpath" org.bukkit.craftbukkit.Main --nogui > console.log 2>&1
) || {
  tail -80 "$test_dir/console.log"
  exit 1
}
if [ ! -f "$test_dir/entityrender-test.result" ] || [ "$(cat "$test_dir/entityrender-test.result")" != PASS ]; then
  tail -100 "$test_dir/console.log"
  exit 1
fi
echo "Paper integration checks passed; artifacts: $test_dir"
