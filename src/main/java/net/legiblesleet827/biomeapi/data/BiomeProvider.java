package net.legiblesleet827.biomeapi.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Objects;

public abstract class BiomeProvider implements DataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private final DataGenerator generator;
    private final String modid;

    public BiomeProvider(DataGenerator generator, String modid) {
        this.generator = generator;
        this.modid = modid;
    }

    @Override
    public void run(HashCache hashCache) {
        Path outputFolder = this.generator.getOutputFolder();

        for (CustomBiomeBuilder builder : addBiomes()) {
            try {
                String jsonStr = GSON.toJson(builder.serialise());
                String hashStr = SHA1.hashUnencodedChars(jsonStr).toString();
                Path path = outputFolder.resolve(String.format("data/%s/worldgen/biome/%s.json", this.modid, builder.name));
                if (!Objects.equals(hashCache.getHash(outputFolder), hashStr) || !Files.exists(path)) {
                    Files.createDirectories(path.getParent());

                    try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                        writer.write(jsonStr);
                    }
                }

                hashCache.putNew(path, hashStr);
            } catch (IOException ex) {
                LOGGER.error("Could not create biome", outputFolder, ex);
            }
        }
    }

    protected abstract Collection<CustomBiomeBuilder> addBiomes();

    @Override
    public String getName() {
        return "Biome Generator";
    }
}
