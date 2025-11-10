package starapple.fabric.data;

import starapple.entity.ModStatusEffects;
import starapple.item.ModBlocks;
import starapple.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLangProvider extends FabricLanguageProvider {
    public ModLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        ModStatusEffects.data.forEach((id, data) -> translationBuilder.add(data.get(), data.name));
        ModBlocks.data.forEach((id, data) -> translationBuilder.add(data.get(), data.name));
        ModItems.data.forEach((id, data) -> {
            if (!ModBlocks.data.containsKey(id)) translationBuilder.add(data.get(), data.name);
        });
    }
}
