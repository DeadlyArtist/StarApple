package starapple.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import starapple.entity.ModStatusEffects;
import starapple.item.ModItems;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Unique
    private final LivingEntity self = (LivingEntity) (Object) this;

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void injectTakeDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.isOf(DamageTypes.WITHER) && self.hasStatusEffect(ModStatusEffects.WITHER_IMMUNITY.getEntry())) {
            cir.setReturnValue(false);
        }
    }
}
