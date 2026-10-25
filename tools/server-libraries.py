#!/usr/bin/env python3
"""Print the exact local Paper bundle dependencies, excluding stale sibling versions."""
from pathlib import Path
import sys
import zipfile

server = Path(sys.argv[1]).resolve()
bundles = sorted(server.glob('paper*.jar'))
if len(bundles) != 1:
    raise SystemExit('Expected one Paper bootstrap jar in ' + str(server))
with zipfile.ZipFile(bundles[0]) as jar:
    for line in jar.read('META-INF/libraries.list').decode().splitlines():
        if not line.strip():
            continue
        dependency = server / 'libraries' / line.split('\t')[2]
        if not dependency.is_file():
            raise SystemExit('Missing prepared dependency: ' + str(dependency))
        print(dependency)
