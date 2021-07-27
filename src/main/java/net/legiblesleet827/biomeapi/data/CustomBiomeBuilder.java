package net.legiblesleet827.biomeapi.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Optional;

public class CustomBiomeBuilder {
    public final String name;
    private final ResourceLocation surfaceBuilder;
    private final float depth;
    private final float scale;
    private final float temperature;
    private final float downfall;
    private final Biome.Precipitation precipitation;
    private final Biome.TemperatureModifier temperatureModifier;
    private final Biome.BiomeCategory category;
    private final Boolean playerSpawnFriendly;
    private final int fogColor;
    private final int waterColor;
    private final int waterFogColor;
    private final int skyColor;
    private final Integer foliageColorOverride;
    private final Integer grassColorOverride;
    private final BiomeSpecialEffects.GrassColorModifier grassColorModifier;

    public CustomBiomeBuilder(String name, ResourceLocation surfaceBuilder, float depth, float scale, float temperature, float downfall,
                              Biome.Precipitation precipitation, @Nullable Biome.TemperatureModifier temperatureModifier, Biome.BiomeCategory category, @Nullable Boolean playerSpawnFriendly,
                              int skyColor, int fogColor, int waterColor, int waterFogColor, @Nullable Integer grassColor, @Nullable Integer foliageColor, @Nullable BiomeSpecialEffects.GrassColorModifier grassColorModifier) {

        this.name = name;
        this.surfaceBuilder = surfaceBuilder;
        this.depth = depth;
        this.scale = scale;
        this.temperature = temperature;
        this.downfall = downfall;
        this.precipitation = precipitation;
        this.temperatureModifier = temperatureModifier;
        this.category = category;
        this.playerSpawnFriendly = playerSpawnFriendly;
        this.skyColor = skyColor;
        this.fogColor = fogColor;
        this.waterColor = waterColor;
        this.waterFogColor = waterFogColor;
        this.grassColorOverride = grassColor;
        this.foliageColorOverride = foliageColor;
        this.grassColorModifier = grassColorModifier;

    }

    public JsonObject serialise() {
        JsonObject json = new JsonObject();

        json.addProperty("surface_builder", this.surfaceBuilder.toString().toLowerCase());
        json.addProperty("depth", this.depth);
        json.addProperty("scale", this.scale);
        json.addProperty("temperature", this.temperature);
        json.addProperty("downfall", this.downfall);
        json.addProperty("precipitation", this.precipitation.toString().toLowerCase());

        if (this.temperatureModifier != null) {
            json.addProperty("temperature_modifier", this.temperatureModifier.toString().toLowerCase());
        }

        json.addProperty("category", this.category.toString().toLowerCase());

        if (this.playerSpawnFriendly != null) {
            json.addProperty("player_spawn_friendly", this.playerSpawnFriendly);
        }

        JsonObject biomeEffects = new JsonObject();
        biomeEffects.addProperty("sky_color", this.skyColor);
        biomeEffects.addProperty("fog_color", this.fogColor);
        biomeEffects.addProperty("water_color", this.waterColor);
        biomeEffects.addProperty("water_fog_color", this.waterFogColor);
        if (this.grassColorOverride != null) {
            biomeEffects.addProperty("grass_color", this.grassColorOverride);
        }
        if (this.foliageColorOverride != null) {
            biomeEffects.addProperty("foliage_color", this.foliageColorOverride);
        }
        if (this.grassColorModifier != null) {
            biomeEffects.addProperty("grass_color_modifier", this.grassColorModifier.toString().toLowerCase());
        }

        json.add("effects", biomeEffects);
        json.add("starts", new JsonArray());
        json.add("spawners", new JsonObject());
        json.add("spawn_costs", new JsonObject());
        json.add("carvers", new JsonObject());
        json.add("features", new JsonArray());

        return json;
    }
}
