# Storm Staff

A Fabric mod for Minecraft **1.21.1** that adds one item: the **Storm Staff**.

![Storm Staff](src/main/resources/assets/stormstaff/icon.png)

## What it does

- **Right-click** to call a lightning bolt down on whatever you're aiming at (up to 48 blocks away).
- Each strike costs **1 durability** (64 total) and puts the staff on a **2-second cooldown**.
- Aiming at nothing just fizzles — no durability lost.
- Mobs killed by your bolts count as yours (the bolt's channeler is set to you), so advancements and drops work as expected.
- Found in the **Combat** creative tab. Rare rarity, enchantment glint.

## Recipe

```
 .  D  L
 .  B  D
 B  .  .
```
`L` = Lightning Rod, `D` = Diamond, `B` = Blaze Rod

## Building

Requires Java 21.

```sh
./gradlew build
```

The mod jar ends up in `build/libs/storm-staff-1.0.0.jar`. Drop it in your `mods/` folder along with
[Fabric Loader](https://fabricmc.net/use/) and [Fabric API](https://modrinth.com/mod/fabric-api).

To test in a dev client: `./gradlew runClient`.

## Code layout

| File | Purpose |
| --- | --- |
| `StormStaffMod.java` | Mod entrypoint |
| `item/ModItems.java` | Registers the item and adds it to the Combat tab |
| `item/StormStaffItem.java` | Right-click lightning behaviour, cooldown, tooltip |
| `resources/assets/stormstaff/` | Model, texture, translations |
| `resources/data/stormstaff/recipe/` | Crafting recipe |
