package starapple.utils;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.data.DataWriter;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class DataProviderUtils {
    public static RegistryWrapper.WrapperLookup getGlobalWrapperLookup() {
        return BuiltinRegistries.createWrapperLookup();
    }

    public static RegistryOps<JsonElement> getGlobalJsonRegistryOps() {
        return getGlobalWrapperLookup().getOps(JsonOps.INSTANCE);
    }

    public static <T> JsonElement toJson(RegistryOps<JsonElement> registryOps, Codec<T> codec, T value) {
        return codec.encodeStart(registryOps, value).getOrThrow();
    }
}
