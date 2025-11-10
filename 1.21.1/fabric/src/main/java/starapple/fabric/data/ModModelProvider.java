package starapple.fabric.data;

import starapple.item.ModBlocks;
import starapple.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        ModBlocks.data.forEach((id, data) -> {
            if (data.modelSupplier != null) data.modelSupplier.accept(blockStateModelGenerator, data.get());
        });
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        ModItems.data.forEach((id, data) -> {
            if (data.modelSupplier != null) data.modelSupplier.accept(itemModelGenerator, data.get());
        });
    }
}
