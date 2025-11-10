package starapple.utils;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.data.DataWriter;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class DataProviderUtils {
    public static <T> JsonElement toJson(RegistryWrapper.WrapperLookup registryLookup, Codec<T> codec, T value) {
        RegistryOps<JsonElement> registryops = registryLookup.getOps(JsonOps.INSTANCE);
        return codec.encodeStart(registryops, value).getOrThrow();
    }
}
