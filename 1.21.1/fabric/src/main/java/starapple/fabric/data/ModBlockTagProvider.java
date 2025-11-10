package starapple.fabric.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import starapple.item.ModBlocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final Map<TagKey<Block>, ArrayList<Block>> tags = new HashMap<>();

    public static void addToTag(TagKey<Block> tag, Block block) {
        tags.computeIfAbsent(tag, k -> new ArrayList<>()).add(block);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        ModBlocks.data.forEach((id, data) -> data.tags.forEach(tag -> addToTag(tag, data.get())));

        tags.forEach((tag, blocks) -> {
            var tagBuilder = getOrCreateTagBuilder(tag);
            blocks.forEach(block -> tagBuilder.add(block));
        });
    }
}
