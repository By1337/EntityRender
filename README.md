# EntityRender

Библиотека виртуальных сущностей для Paper и дампер metadata Minecraft.
[Гайд по API](API_USAGE.md): создание сущностей, свойства, зрители, клики и сцены.

## Модули

- `api` — публичные интерфейсы без NMS.
- `core` — реализация для версии Paper текущей ветки.
- `bukkit` — плагин, загружающий подходящие API/core из встроенных BPatch-архивов.
- `dumper` — самостоятельный инструмент дампа, захвата defaults и генерации Java-кода.

`main` содержит core для **Paper 26.3 / Java 25** и модуль `bukkit`.
Предыдущие реализации хранятся в ветках с именами версий Minecraft.
API, core и Bukkit-загрузчик компилируются с `--release 21`;
для сборки core 26.x и запуска соответствующего сервера нужен JDK 25.

## Bukkit-плагин

Установи `EntityRender-2.0.0.jar` и **BLib 1.10.8+** в `plugins/`.
В `plugin.yml` потребителя добавь `depend: [EntityRender]`,
Maven-зависимости на EntityRender объявляй как `provided`.
API и core также можно встроить непосредственно в плагин-потребитель.

Поддерживаются точные версии Paper:
**1.21.4, 1.21.5, 1.21.6, 1.21.7, 1.21.8, 1.21.9, 1.21.10, 1.21.11,
26.1.2, 26.2, 26.3**. Для каждой загружается собственная пара API/core.
API между версиями различается: компилируй потребителя под целевой сервер.
Неизвестная версия сервера отклоняется. Folia пока не поддерживается.

Загрузчик использует `bpatch-maven-plugin`, `PatchArchive` и BLib `ClasspathUtil`.
Распакованные JAR и `.fixed` хранятся в `plugins/EntityRender/.runtime`.
При повторном запуске распаковываются только отсутствующие модули. Смена версии
загрузчика, времени изменения его JAR или версии сервера очищает весь этот кеш.
Модули `api` и `core` сами по себе не зависят от BLib;
Paper API, NMS и серверные библиотеки имеют scope `provided`.

## Сборка и публикация

Все артефакты EntityRender публикуются без `-SNAPSHOT` в
`https://repo.by1337.space/releases`, Maven server id — `bdev-repo`.
Учётные данные должны находиться в пользовательском `~/.m2/settings.xml`.

```sh
# Собрать и установить все версии, затем Bukkit-плагин:
python3 tools/release.py install

# Собрать и опубликовать все версии:
python3 tools/release.py deploy

# Опубликовать уже собранные локальные релизы:
python3 tools/release.py deploy --use-installed
```

Скрипт требует JDK 25 и берёт предыдущие реализации из Git-веток во временные
каталоги. Текущую версию и `bukkit` собирает из рабочего дерева.
Результаты сохраняются в `dist/releases/`, плагин — `EntityRender-2.0.0.jar`.
После установки всех версий для обычной сборки достаточно `mvn clean package`.

Координаты Maven:

- `dev.by1337.entityrender:entity-render-api:2.0.0+<версия Minecraft>`;
- `dev.by1337.entityrender:entity-render-core:2.0.0+<версия Minecraft>`;
- `dev.by1337.entityrender:bukkit:2.0.0`.

## Дампер

Дампер работает на JDK 21+ без сторонних библиотек. Он использует локальные
JAR Minecraft/Paper, ничего не скачивает и не ремапит.

```sh
sh build.sh
java -jar dumper/target/entity-dumper.jar --help

# Или собрать только дампер через Maven:
mvn -pl dumper -am package
```

`dump` запускает отдельную JVM, выполняет bootstrap Minecraft, снимает схему
metadata и hex-образцы сериализации. Полноценный сервер и мир не создаются.
Нужен JAR с классами под Mojang-именами, а не внешний bundler.

```sh
java -jar dumper/target/entity-dumper.jar dump \
  --minecraft /path/to/mojang-named-server.jar \
  --bundle /path/to/original-server-bundler.jar \
  --java /path/to/target-jdk/bin/java \
  --out dumps/26.3
```

`--java` выбирает JVM целевой версии Minecraft. Зависимости извлекаются из
`META-INF/libraries.list` bundler в `.entityrender/cache` с проверкой SHA-256.
Вместо `--bundle` можно передать `--libraries /path/to/libraries`.
Если в каталоге несколько версий зависимостей, передай оба параметра:
список bundler определит точные файлы и их контрольные суммы.

Схема — UTF-8 TSV со стабильным порядком строк:

| Файл | Содержание |
| --- | --- |
| `version.tsv` | Версия формата, Minecraft и protocol |
| `entities.tsv` | Классы, наследование и registry keys сущностей |
| `fields.tsv` | Metadata indices, serializers и NMS-типы |
| `serializers.tsv` | Имена и сетевые ID serializers |
| `hex.tsv` | Образцы сериализации, payload hex и результаты проверки |

Hex содержит только payload значения. Векторы проверяются через NMS
encode → decode → encode. Неподдержанные образцы явно отмечаются в отчёте.
Начальные значения сущностей снимаются отдельно командой `capture`.

## Захват defaults

```sh
java -jar dumper/target/entity-dumper.jar capture \
  --server /path/to/prepared-paper-server \
  --java /path/to/target-jdk/bin/java \
  --out /tmp/defaults-26.3
```

Нужен подготовленный Paper с каталогами `versions/` и `libraries/`.
Команда запускает временный сервер на localhost, загружает мир и реестры,
снимает значения и останавливает процесс. Исходный сервер только читается;
мир, плагин-проба и `console.log` остаются в `/tmp/entityrender-capture-*`.
Проба текущей ветки предназначена для Paper 26.3.

Результат: `capture-version.tsv`, `metadata-defaults.tsv`, `spawn-defaults.tsv`.
После проверки помести файлы в
`core/src/main/resources/dev/by1337/entityrender/defaults/` и пересобери core.
Runtime проверяет совместимость версии дампа.

## Генерация и сравнение

```sh
# Черновики API/core и типизированный GeneratedMetadata:
java -jar dumper/target/entity-dumper.jar generate \
  --schema dumps/26.3 --out generated/26.3 \
  --defaults core/src/main/metadata/defaults.tsv

# Обновить поддерживаемые metadata и API/core в исходниках:
sh generate-metadata.sh dumps/26.3
sh generate-runtime.sh dumps/26.3

# Сравнить схемы или потребовать точного совпадения:
java -jar dumper/target/entity-dumper.jar diff --old dumps/26.2 --new dumps/26.3
java -jar dumper/target/entity-dumper.jar verify --expected dumps/26.3 --actual /tmp/new-dump
```

`generate-metadata.sh` обновляет `GeneratedMetadata.java` с проверенными правилами
из `core/src/main/metadata/defaults.tsv`. `generate-runtime.sh` обновляет
генерируемые интерфейсы и реализации, удаляя устаревшие файлы по списку
`.generated-runtime-files`. Оба скрипта используют `java-format`.
Генерация не заменяет проверку NMS-контрактов при поддержке новой версии.

## Проверка дампера

```sh
sh test.sh /path/to/mojang-named-server.jar \
  --bundle /path/to/original-server-bundler.jar \
  --java /path/to/target-jdk/bin/java
```

Скрипт сравнивает два независимых дампа и эталон текущей версии из
`dumper/src/test/resources/golden/`, компилирует генерируемые скелеты и проверяет
hex-векторы, генератор и выбор Paper-bundler. Для запуска самого скрипта выбери
подходящий JDK через `JAVA_HOME`.
