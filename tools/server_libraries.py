#!/usr/bin/env python3
"""Resolve prepared Paper dependencies without stale versions or incomplete downloads."""
from pathlib import Path
import sys
import zipfile


def select_bundle(server):
    candidates = sorted(server.glob('paper*.jar'), key=lambda path: (path.name != 'paper.jar', path.name))
    for path in candidates:
        try:
            with zipfile.ZipFile(path) as jar:
                if 'META-INF/libraries.list' in jar.namelist():
                    return path
        except zipfile.BadZipFile:
            continue
    raise ValueError('No valid Paper bootstrap jar in ' + str(server))


if __name__ == '__main__':
    bundle_only = sys.argv[1] == '--bundle'
    server = Path(sys.argv[2] if bundle_only else sys.argv[1]).resolve()
    bundle = select_bundle(server)
    if bundle_only:
        print(bundle)
    else:
        with zipfile.ZipFile(bundle) as jar:
            for line in jar.read('META-INF/libraries.list').decode().splitlines():
                if not line.strip():
                    continue
                dependency = server / 'libraries' / line.split('\t')[2]
                if not dependency.is_file():
                    raise SystemExit('Missing prepared dependency: ' + str(dependency))
                print(dependency)
