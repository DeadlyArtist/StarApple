package starapple.utils;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

public class StatusEffectUtils {

    public static RegistryEntry<StatusEffect> getEntry(StatusEffect statusEffect) {
        return Registries.STATUS_EFFECT.getEntry(statusEffect);
    }
}
