package starapple.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import starapple.entity.ModStatusEffects;
import starapple.utils.Lazy;

import java.util.function.Supplier;

public class ModFoodComponents {
    public static final Lazy<FoodComponent> STAR_APPLE = register(() -> new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(1.2F)
            .statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1000, 1), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 500, 2), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 3000, 0), 1.0F)
            .statusEffect(new StatusEffectInstance(ModStatusEffects.WITHER_IMMUNITY.getEntry(), 3000, 0), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 8), 1.0F)
            .alwaysEdible()
            .build());
    public static final Lazy<FoodComponent> ENCHANTED_STAR_APPLE = register(() -> new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(2F)
            .statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1000, 1), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1000, 1), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 0), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0), 1.0F)
            .statusEffect(new StatusEffectInstance(ModStatusEffects.WITHER_IMMUNITY.getEntry(), 6000, 0), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 12), 1.0F)
            .alwaysEdible()
            .build());

    public static Lazy<FoodComponent> register(Supplier<FoodComponent> food) {
        return new Lazy<>(food);
    }
}
