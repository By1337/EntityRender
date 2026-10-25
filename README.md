# EntityRender

Библиотека виртуальных сущностей и инструменты дампа / генерации.
Runtime текущей ветки предназначен для **Paper 1.21.10 / Java 21**.
Предыдущие версии фиксируются отдельными ветками; `main` обновляется вперёд.
Проверенные сборки и результаты интеграционных проверок перечислены в [VERSIONS.md](VERSIONS.md).

## Runtime

Модули `api` и `core` не зависят от BLib и не встраивают сторонние библиотеки.
Paper API и NMS предоставляются сервером (`provided`). `dumper` работает на чистом JDK.
EntityRender — библиотека для плагина-потребителя, отдельный Bukkit-плагин не требуется.

Реализована вся иерархия зарегистрированных клиентских типов: типизированные интерфейсы,
свойства metadata и фабрики (`createCreeper`, `createWolf` и т. д.). ArmorStand и Display
сохраняют специально написанный API. В публичных сигнатурах используются Bukkit / Adventure / JOML
типы, без NMS, Mojang Codec и собственного набора enum-копий.

```java
// Вызывать из onEnable / серверного потока.
EntityRenderer renderer = EntityRender.create(this);
VirtualArmorStand stand = renderer.createArmorStand(location);
stand.setInvisible(true);
stand.setGravity(false);
stand.setSmall(true);
stand.setCustomName(Component.text("Приз"));
stand.setCustomNameVisible(true);
stand.setEquipment(EquipmentSlot.HEAD, new ItemStack(Material.DIAMOND));
stand.show(player);

VirtualTextDisplay text = renderer.createTextDisplay(location.clone().add(0, 1, 0));
text.setText(Component.text("Награда"));
text.setBillboard(Display.Billboard.CENTER);
text.show(player);

// Удаление одной сущности; renderer автоматически закрывается при disable владельца.
stand.close();
```

Точка входа: `dev.by1337.entityrender.EntityRender`, интерфейс сервиса:
`dev.by1337.entityrender.api.EntityRenderer`. Нужны оба JAR: api и core.
`create` регистрирует одну общую задачу отправки за тик; `createManual` позволяет
потребителю самому вызывать `renderer.flush()`. Этот `flush` отправляет pending
изменения сущностей, **не делает flush Netty-канала**. Транспорт использует
`Connection.send(packet, null, false)`; канал флушит сервер.

Сущность хранит массив значений metadata и BitSet изменений, общую immutable-схему,
координаты и зрителей. Полноценного NMS Entity на каждую виртуальную сущность нет.
NMS используется для сериализаторов и готовых пакетов. В горячем пути нет reflection.
В очередь попадают только изменённые сущности; несколько изменений поля за тик
объединяются. Metadata-пакет создаётся один раз и используется для всех зрителей.
Снимок для нового зрителя не сбрасывает dirty-состояние остальных. После ошибки
отправки сущность остаётся в очереди для повторной попытки.

Все операции выполняются в потоке, создавшем renderer. Публичная фабрика требует
основной поток Paper; Folia пока не поддерживается. Vector, Location, Transformation,
BlockData и ItemStack не сохраняются как изменяемые ссылки от потребителя; геттеры
возвращают независимые значения. Pose ArmorStand задаётся Bukkit EulerAngle в радианах.
Pose сущности, billboard, text alignment и item transform переводятся явно, без ordinal.
Для редких составных значений доступны SNBT-свойства с суффиксом `Snbt`; packed-поля
остаются доступны наряду с helpers. Своих NMS enum-копий в API нет.

Для остальных типов один NMS-прототип на тип захватывает настоящее начальное состояние
при первом создании в renderer. После этого используются общие кешированные layouts,
без NMS Entity на виртуальный экземпляр. Прототипы не добавляются в мир и не тикают.
Игроки и FishingHook требуют отдельного контекста; Marker является серверным типом.
Эти три типа намеренно не входят в общую фабрику.

Зрители управляются через show/hide либо `renderer.createTracker(center, range)`.
Один tracker обслуживает сцену из нескольких сущностей; позиции игроков снимаются
один раз на мир/тик и используется пространственный индекс. Сущность может принадлежать
только одному tracker. При выходе, смене мира и respawn игрок исключается из зрителей;
tracker восстанавливает подходящую видимость на следующем обновлении. EntityRender не запускает
AI/физику, не добавляет сущности в мир, не создаёт коллизии и не начисляет урон.

`entity.setInteractionHandler(handler)` получает ATTACK / INTERACT / INTERACT_AT на
серверном потоке. Native interact-пакеты перехватываются до vanilla только для
принадлежащих renderer ID; callback получает только текущий зритель из того же мира.
Различаются main/off hand и вторичное действие. Хуки снимаются при выходе/закрытии.

Сборка без скачивания зависимостей из уже подготовленного Paper-сервера:

```sh
JAVA_HOME=/path/to/jdk-21 sh build-runtime.sh /path/to/prepared-paper-1.21.4-server
```

Либо весь Maven reactor: `JAVA_HOME=/path/to/jdk-21 mvn package`.
Maven собирает артефакты; интеграционные проверки запускаются явными скриптами ниже,
без JUnit и других тестовых библиотек.

### Проверка runtime

```sh
# Bootstrap vanilla + настоящий NMS codec + захват пакетов без подключения клиента:
JAVA_HOME=/path/to/jdk-21 sh test-runtime.sh /path/to/prepared-paper-1.21.4-server /path/to/unbundled-vanilla-1.21.4.jar

# Запускает отдельный временный Paper-сервер, без изменения исходного сервера:
JAVA_HOME=/path/to/jdk-21 sh test-paper.sh /path/to/prepared-paper-1.21.4-server
```

Проверяются native metadata indices/serializers, полные packet roundtrip, dirty batching,
общий пакет для нескольких зрителей, повторная отправка после сбоя, сброс в default,
защита от aliasing, потоковая принадлежность, lifecycle и неизменившиеся значения.
Живой Paper-тест дополнительно проверяет Adventure/предметы/BlockData, выделение ID
через сервер, отсутствие сущностей в мире и все defaults четырёх типов против
реальных инициализированных NMS-объектов. Реальный клиент и картинка пока не проверялись.
Дополнительно весь реестр проходит создание, повторное создание без NMS-прототипа,
getter/setter roundtrip всех генерируемых свойств и точное сравнение native metadata
байтов. Проверяются spawn-only состояния FallingBlock/опыта, channel hooks и trackers.

## Сборка

Нужен JDK 21+. Ни BLib, ни ASM, ни JSON-библиотек, ни других сторонних зависимостей
у инструмента нет. Minecraft запускается со своими оригинальными зависимостями.

```sh
sh build.sh
java -jar dumper/target/entity-dumper.jar --help
```

Также предусмотрена Maven-сборка `mvn package`.

## Дамп локального Minecraft

```sh
java -jar dumper/target/entity-dumper.jar dump \
  --minecraft /path/to/remapped_server_1.21.4.jar \
  --bundle /path/to/minecraft_server_1.21.4.jar \
  --java /path/to/jdk-21/bin/java \
  --out dumps/1.21.4
```

`--minecraft` — JAR с классами Minecraft под Mojang-именами, не внешний bundler.
Инструмент ничего не скачивает и не ремапит. Для необфусцированных сборок нужен
такой же JAR с непосредственно доступными классами.

`--bundle` — оригинальный server bundler с `META-INF/libraries.list`. Если рядом с
Minecraft JAR лежит `minecraft_server_<version>.jar`, он обнаруживается автоматически.
Зависимости извлекаются в `.entityrender/cache/libraries`, проверяются по SHA-256.
Вместо bundler можно указать `--libraries <directory>` с готовым classpath-зависимостями
именно этой версии. Не складывайте в него разные версии одной библиотеки.

Launcher запускает отдельную JVM с изолированным рабочим каталогом. Worker выполняет
Minecraft bootstrap, читает metadata, записывает схему и завершается. Мир, сетевой
порт и полноценный игровой сервер не создаются. Нужную JVM выбирает вызывающий
через `--java`; автоматически Java не устанавливается.

**Дампер проверен на remapped vanilla 1.21.4 / Java 21 и vanilla 26.3 / Java 25.** Другие версии пока не проверены:
при изменениях NMS обновляем reflection-адаптер worker и набор образцов. Общее имя
Mojang-класса не гарантирует одинаковые методы/конструкторы между версиями.

## Формат схемы v1

UTF-8 TSV с заголовками, стабильным порядком и без времени генерации:

| Файл | Содержание |
| --- | --- |
| `version.tsv` | Версия формата, Minecraft и protocol |
| `entities.tsv` | Полное имя класса, родитель, abstract, registry keys сущностей |
| `fields.tsv` | Владелец поля, имя, metadata index, serializer, NMS generic type |
| `serializers.tsv` | Имя serializer, его network ID и NMS generic type |
| `hex.tsv` | Serializer, образец, вход, статус, raw payload hex, примечание |

Унаследованные поля не дублируются у потомков. Проверяются неизвестные serializers,
невалидные и повторяющиеся metadata indices в иерархии.

Defaults пока **не снимаются**: `defaultStatus=NOT_CAPTURED`. По наличию статического
accessor нельзя достоверно восстановить начальное значение экземпляра.

## Hex-векторы

Каждый `PASS` получен настоящим NMS `EntityDataSerializer.codec()`, через
`RegistryFriendlyByteBuf`, с проверкой encode → decode → encode и полного чтения
буфера. Hex содержит **только payload значения**, без metadata index, serializer ID
и завершающего байта metadata-пакета. Сетевой ID serializer хранится отдельно.

На 1.21.4: **117 векторов для 27 из 31 serializers**. Проверяются границы VarInt/VarLong,
float bits, UTF-8, позиции блоков, optional, enum, компоненты, NBT, блоки, предметы,
частицы, rotations, vector, quaternion и villager data. Это набор образцов, а не
исчерпывающая проверка всех допустимых значений каждого serializer.

На 26.3: **139 успешных векторов**, 15 serializers без адаптеров и один явно
пропущенный образец непустого ItemStack (нужны загруженные item components).
Пустой ItemStack проверяется. Статусы сохраняются для каждого образца.

`CAT_VARIANT`, `FROG_VARIANT`, `WOLF_VARIANT`, `PAINTING_VARIANT` на 1.21.4 пока имеют статус
`UNSUPPORTED`: нужны образцы и корректно загруженные динамические реестры. Они
явно записаны в отчёт и не засчитываются как пройденные тесты. Неизвестные новые
serializers также получают `UNSUPPORTED`; ошибка уже поддержанного образца
прерывает дамп, а не маскируется как пропуск.

Для проверки собственной реализации в тестах доступен мост:

```java
int checked = HexVerifier.verify(schemaPath, (serializer, sample, input) -> {
    // Собрать описанное значение и записать payload вашим encoder.
    // Вернуть реальные байты. Все PASS-образцы должны быть обработаны.
    return encodeWithYourImplementation(serializer, sample, input);
});
```

Для чисел `input` содержит десятичное значение, для FLOAT — `float-bits:<8 hex>`.
Составные значения описаны префиксами (`block-pos:`, `literal:`, `nbt:`, `item:`,
`villager:`); остальные именованные образцы определены в `HexFixtures.java`.
`UNSUPPORTED` не передаются вашему encoder. Класс `HexVerifier` нужен только тестам,
runtime EntityRender не обязан зависеть от dumper.

## Генерация и сравнение

`GeneratedMetadata` теперь содержит **типизированные MetadataKey и MetadataLayout**,
а не только индексы. Namespace повторяет NMS-пакеты и вложенные классы:

```java
GeneratedMetadata.Entity.DATA_SHARED_FLAGS_ID;
GeneratedMetadata.decoration.ArmorStand.DATA_HEAD_POSE;
GeneratedMetadata.decoration.ArmorStand.LAYOUT;
GeneratedMetadata.Display.TextDisplay.DATA_TEXT_ID;
GeneratedMetadata.Display.TextDisplay.LAYOUT;
```

Поля сохраняют оригинальные NMS-имена. Одинаковые простые имена классов в разных
пакетах не сталкиваются. Общего ручного `Layouts` больше нет. Наследование полей и
соответствие `EntityDataSerializers` генерируются из дампа; сериализация остаётся NMS.

Проверенные defaults хранятся в `core/src/main/metadata/defaults.tsv`: целевая сущность,
владелец поля, имя поля, Java-выражение. Это входные правила генерации, а не runtime-конфиг.
Они учитывают изменения в конструкторе (например, здоровье ArmorStand = 20).
Генератор отклоняет устаревшие имена и defaults чужих сущностей. Layout без известных
defaults генерируется, но создать `MetadataState` из него нельзя — отсутствующие
значения не заменяются на нули или null. Копирование mutable JOML-значений генерируется
в ключах отдельно от NMS serializer, чтобы сохранить защиту от aliasing.

Обновление поддерживаемого runtime-файла и его форматирование:

```sh
sh generate-metadata.sh dumper/src/test/resources/golden/1.21.4
```

Скрипт вызывает `java-format` только для `GeneratedMetadata.java`. Генерируемый Java
файл хранится в Git и показывает изменения по сущностям; архив прошлой генерации для
сравнения не нужен. Полные черновики API/core создаются отдельной командой:

```sh
java -jar dumper/target/entity-dumper.jar generate --schema dumps/1.21.4 --out generated/1.21.4
java -jar dumper/target/entity-dumper.jar diff --old dumps/1.21.4 --new dumps/1.21.5
java -jar dumper/target/entity-dumper.jar verify --expected dumps/1.21.4 --actual /tmp/new-dump
```

`generate` создаёт компилируемые интерфейсы и абстрактные core-скелеты с наследованием,
индексами и комментариями о NMS-типах, а также типизированный `GeneratedMetadata.java`.
Для defaults передайте `--defaults core/src/main/metadata/defaults.tsv`; без этого
все layouts будут иметь неизвестные defaults. Типизированный файл компилируется с
NMS и runtime metadata-модулем; структурные API/core-скелеты компилируются отдельно.
Это черновики: семантические имена методов,
битовые флаги и Bukkit-преобразования дорабатываются отдельно. Для неизвестных
публичных типов используется `Object` с TODO; NMS-типы не попадают в сигнатуры API.
Генерация не перезаписывает существующий каталог.

`diff` сравнивает строки по стабильным ключам; изменение ID поля выводится как
изменение поля, а не новой сущности. `verify` требует побайтового совпадения всех
пяти файлов и возвращает ненулевой exit code при расхождении.

## Интеграционная проверка

```sh
sh test.sh /path/to/remapped_server_1.21.4.jar --bundle /path/to/original-bundle.jar --java /path/to/java
```

Два независимых bootstrap-процесса должны дать одинаковые схемы и hex, совпадающие
с сохранённым эталоном `dumper/src/test/resources/golden/1.21.4`. Затем
генерируются и компилируются все скелеты, проверяются известные сетевые байты
и отклонение неправильного encoder. Артефакты остаются в напечатанном временном
каталоге для просмотра.
