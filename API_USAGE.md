# EntityRender: быстрый старт

Примеры — для `main` (**Paper 26.3 / Java 25**). Для другого сервера бери
соответствующую версионную ветку и её пару `api` + `core`.

## Подключение

Установи `EntityRender-2.0.0.jar` и BLib **1.10.8+** в `plugins/`.
EntityRender распакует API и core именно для версии сервера (Paper 1.21.4–26.3,
см. [список в README](README.md)). В `plugin.yml` потребителя добавь `depend: [EntityRender]`.

Maven-зависимость для компиляции выбирай под целевой сервер:

```xml
<repositories>
    <repository>
        <id>bdev-repo</id>
        <url>https://repo.by1337.space/releases</url>
    </repository>
</repositories>
<dependencies>
    <dependency>
        <groupId>dev.by1337.entityrender</groupId>
        <artifactId>entity-render-core</artifactId>
        <version>2.0.0+26.3</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

`core` подтянет `api` транзитивно. Плагин EntityRender предоставляет их во время
работы. API разных версий различается; компилируй потребителя для нужной версии.
Можно также по-прежнему встроить оба модуля в свой плагин без установки EntityRender.

## Создать и показать

Создай один renderer на плагин или владельца сцен, например в `onEnable()`:

```java
import dev.by1337.entityrender.EntityRender;
import dev.by1337.entityrender.api.EntityRenderer;

EntityRenderer renderer = EntityRender.create(this);
```

Дальше `renderer` — этот экземпляр, `location` — Bukkit `Location` с миром,
`player` — зритель в том же мире. Только `EntityRender.create/createManual` вызывай на main, например из `onEnable`.
Создавать и менять сущности, управлять сценами и вызывать `flush` можно из виртуального
потока или другой async-задачи.

```java
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

var stand = renderer.createArmorStand(location);
stand.setInvisible(true);
stand.setGravity(false);
stand.setSmall(true);
stand.setEquipment(EquipmentSlot.HEAD, new ItemStack(Material.DIAMOND));
stand.setCustomName(Component.text("Приз"));
stand.setCustomNameVisible(true);
stand.show(player);

// Передвижение и metadata обновляются у всех текущих зрителей.
stand.teleport(location.clone().add(0, 1, 0));
stand.setRotation(90, 0); // yaw / pitch в градусах
stand.setGlowing(true);

```

Сначала настрой сущность, затем вызывай `show`: после публикации игрок получит готовый снимок.
Изменения после показа накапливаются и публикуются общей задачей раз в серверный тик.
`show/hide/close` тоже проходят через публикацию. `getViewers()` возвращает снимок
допущенных отправкой зрителей, поэтому сразу после `show()` он ещё может быть пустым.
`stand.hide(player)` снимает запрос показа одному игроку; `stand.close()` удаляет её
у всех зрителей после ближайшей публикации и освобождает. Повторный `show(player)` отправляет текущее полное состояние.

## Текст, предметы и мобы

```java
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;

var text = renderer.createTextDisplay(location.clone().add(0, 2, 0));
text.setText(Component.text("Нажми, чтобы открыть кейс"));
text.setBillboard(Display.Billboard.CENTER);
text.setShadowed(true);
text.show(player);

var item = renderer.createItemDisplay(location);
item.setItemStack(new ItemStack(Material.DIAMOND));
item.setItemDisplayTransform(ItemDisplay.ItemDisplayTransform.FIXED);
item.show(player);

var creeper = renderer.createCreeper(location.clone().add(2, 0, 0));
creeper.setPowered(true);
creeper.setCustomName(Component.text("Виртуальный крипер"));
creeper.setCustomNameVisible(true);
creeper.show(player);
```

Есть фабрики `createWolf`, `createZombie`, `createBlockDisplay` и остальные.
Для динамического типа — `renderer.create(EntityType.ZOMBIE, location)`;
результат имеет общий интерфейс `VirtualEntity`. Специфичные методы удобнее получать
через типизированную фабрику и `var`. Полный список — в [GeneratedEntityFactories](api/src/main/java/dev/by1337/entityrender/api/GeneratedEntityFactories.java).

Это клиентские сущности: AI, физики, серверных коллизий и настоящего урона нет.
Игроки, FishingHook и серверный Marker исключены из общей фабрики.

## Клики

```java
import dev.by1337.entityrender.api.EntityInteraction;

creeper.setInteractionHandler(event -> {
    if (event.type() == EntityInteraction.Type.ATTACK) {
        event.player().sendMessage(Component.text("Ударил виртуального крипера"));
    } else {
        event.player().sendMessage(Component.text("Нажал на крипера"));
    }
});
```

Callback приходит на серверном потоке только для текущего зрителя.
`event.entity()` — сущность, `hand()` — рука, `hitPosition()` — локальная точка
попадания; последние два значения могут быть `null`. `secondaryAction()` означает
вторичное действие/sneaking. В 26.x атака передаётся отдельным пакетом: для неё
sneaking берётся из серверного состояния игрока. Правый клик приходит как `INTERACT_AT`;
в старых версиях также встречается `INTERACT`. `setInteractionHandler(null)` снимает обработчик.

## Автоматические зрители для сцены

Вместо ручных `show/hide` можно передать сущности tracker:

```java
var scene = renderer.createTracker(location, 32); // радиус в блоках от центра сцены
scene.add(text);
scene.add(item);
scene.add(creeper);

scene.setCenter(location.clone().add(5, 0, 0));
// Это меняет область видимости; сами сущности не перемещаются.

scene.remove(creeper); // скрыть от зрителей tracker, но оставить сущность живой
scene.close();         // снять управление видимостью; сущности остаются живыми
```

Tracker обновляется вместе с renderer каждый тик и показывает сцену игрокам
в радиусе в том же мире. Для управляемых им сущностей используй tracker как владельца
видимости: ручной `hide` он может отменить на следующем обновлении.
Одна сущность может состоять только в одном tracker того же renderer и мира.

Тот же tracker можно использовать для партиклов и звуков, даже без сущностей:

```java
for (var player : scene) {
    player.playSound(location, org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
    player.spawnParticle(org.bukkit.Particle.FLAME, location, 10);
}
// Также доступны scene.forEach(player -> ...) и scene.getViewers().
```

`getViewers()` возвращает неизменяемый снимок игроков в радиусе сцены в том же мире.
Рабочий набор принадлежит render worker и переиспользуется. Снимок хранится на массиве
и создаётся только при изменении состава зрителей или замене сессии игрока; обычное
движение внутри радиуса не требует его копирования. Состав энтити также публикуется
повторно только после добавления/удаления. Итератор сохраняет свой снимок при следующем
обновлении tracker. Читать и обходить его можно из потока анимации; это не меняет требования потокобезопасности самих Bukkit-методов.
До первого расчёта и после `close()` снимок пустой. Он может отставать от движения,
выхода или смены мира игрока до следующего расчёта. У отдельных энтити дополнительно
проверяется отправка их чанка клиенту, поэтому их `getViewers()` может быть уже.

## Обновление и очистка

`EntityRender.create(plugin)` автоматически отправляет изменения каждый тик
и закрывает renderer при disable плагина. При удалении отдельной сцены закрой её
сущности через `close()`; при завершении работы владельца можно вызвать `renderer.close()`.

Если анимационный движок сам управляет моментом отправки:

```java
EntityRenderer manual = EntityRender.createManual(this);
// Создание сущностей и обновления через manual...
manual.flush(); // опубликовать после обновлений; можно вызывать из async/virtual thread
manual.close(); // при завершении работы владельца
```

`flush()` передаёт накопленные изменения общему worker и не ждёт доставки пакетов.
Netty-канал флушит сам сервер. Для автоматического renderer не нужна своя flush-задача.

```java
Thread.ofVirtual().start(() -> {
    var hologram = manualRenderer.createTextDisplay(location);
    hologram.setText(Component.text("Личный текст"));
    hologram.show(player);
    manualRenderer.flush();
    try {
        Thread.sleep(1000);
        hologram.setText(Component.text("Обновлено"));
        manualRenderer.flush();
    } catch (InterruptedException interrupted) {
        Thread.currentThread().interrupt();
    } finally {
        hologram.close();
        manualRenderer.flush();
    }
});
```

Здесь `manualRenderer` заранее создан через `EntityRender.createManual(this)` на main.
Данные Bukkit для анимации подготовь в допустимом для них потоке: возможность менять
виртуальную сущность async не делает произвольные Bukkit-вызовы потокобезопасными.
NMS defaults загружаются из заранее снятого дампа, без создания нативных сущностей.
При исчерпании пула ID его пополнение может ждать main.
Main не должен ждать завершения такого виртуального потока через `join()`.

Рабочие поля атомарны; одновременные изменения разных полей могут попасть в разные
публикации. Публикация сериализована, но целая сцена не является транзакцией.
Предметы отделяются от входа при установке и далее переиспользуются как owned-значения;
внутренний массив metadata не копирует ItemStack повторно на каждом этапе.

`Location`, `Vector`, `ItemStack`, `BlockData` и `Transformation` возвращаются копиями:
после изменения результата геттера передай его обратно в setter. Между мирами сущность
не телепортируется — создай другую. Углы поз ArmorStand (`EulerAngle`) задаются в радианах.

Клиентские эффекты передаются через `entity.playEffect(EntityEffect.WARDEN_SONIC_ATTACK)`.
Живые сущности и ArmorStand поддерживают `swingArm(EquipmentSlot.HAND)` / `OFF_HAND`.
Вызовы можно делать из потока анимации: они попадут в следующую публикацию после
spawn/metadata. Поздние зрители старые эффекты не воспроизводят. Для 26.3 взмах
использует отдельный `ClientboundSwingAnimationPacket`, без создания NMS-сущностей.

Анимация получения урона доступна через `entity.hurtAnimation()` у любого
`VirtualEntity` на всех поддерживаемых версиях, начиная с 1.21.4. Угол направления
удара задаётся в градусах; NaN и бесконечность запрещены. Используется отдельный
`ClientboundHurtAnimationPacket`, а не `playEffect(HURT)`. Вызов попадает в следующую
публикацию после spawn/metadata и не воспроизводится для поздних зрителей.
Здоровье, скорость и звук не меняются; видимость анимации зависит от типа сущности
и клиента. Для ручного renderer после вызова выполни `renderer.flush()`.

`hurtAnimation()` использует направление `0.0F`. При необходимости направление
удара в градусах можно передать явно: `entity.hurtAnimation(90.0F)`.
