package starapple.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import starapple.item.ModBlocks;
import starapple.item.ModItems;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    @WrapOperation(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private boolean wrapTakesDamage(ItemStack instance, Item item, Operation<Boolean> original) {
        return original.call(instance, item) || instance.isOf(ModItems.STAR_APPLE.get()) || instance.isOf(ModItems.ENCHANTED_STAR_APPLE.get());
    }
}
