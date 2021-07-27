package net.legiblesleet827.biomeapi;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BiomeApiMod.MOD_ID)
public class BiomeApiMod {
    public static final String MOD_ID = "biomeapi";

    private static final Logger LOGGER = LogManager.getLogger();

    public BiomeApiMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
