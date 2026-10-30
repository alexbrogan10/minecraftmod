package com.example.stormstaff.item;

import com.example.stormstaff.StormStaffMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public final class ModItems {
    public static final Item STORM_STAFF = Registry.register(
            Registries.ITEM,
            Identifier.of(StormStaffMod.MOD_ID, "storm_staff"),
            new StormStaffItem(new Item.Settings()
                    .maxDamage(StormStaffItem.MAX_DURABILITY)
                    .rarity(Rarity.RARE)));

    private ModItems() {
    }

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.add(STORM_STAFF));
    }
}
