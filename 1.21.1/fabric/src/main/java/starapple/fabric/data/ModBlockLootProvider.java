package starapple.fabric.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import starapple.item.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootProvider extends FabricBlockLootTableProvider {
    public ModBlockLootProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        ModBlocks.data.forEach((id, data) -> {
            if (data.lootSupplier != null) data.lootSupplier.accept(this, data.get());
        });
    }
}
