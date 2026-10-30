package com.example.stormstaff;

import com.example.stormstaff.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StormStaffMod implements ModInitializer {
    public static final String MOD_ID = "stormstaff";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.register();
        LOGGER.info("Storm Staff loaded - mind the weather.");
    }
}
