package starapple.utils;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;

public class DimensionUtils {
    public static RegistryKey<World> getWorld(Identifier id) {
        return RegistryKey.of(RegistryKeys.WORLD, id);
    }

    public static RegistryKey<World> getWorld(String id) {
        return getWorld(Identifier.of(id));
    }
}
