package starapple.entity;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import starapple.ModEntry;
import starapple.entity.StatusEffectData;

import java.util.HashMap;
import java.util.Map;

public class ModStatusEffects {

    public static final Map<Identifier, StatusEffectData> data = new HashMap<>();

    public static final StatusEffectData WITHER_IMMUNITY = register(StatusEffectData.create("WITHER_IMMUNITY", () -> new StatusEffect(StatusEffectCategory.BENEFICIAL, 4738376)));

    public static StatusEffectData register(StatusEffectData.Builder builder) {
        var effect = builder.build();
        data.put(effect.id, effect);
        return effect;
    }

    public static void init() {

    }
}