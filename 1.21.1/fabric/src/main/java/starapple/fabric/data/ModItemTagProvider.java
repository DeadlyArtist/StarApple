package starapple.fabric.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import starapple.item.ModItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    public static final Map<TagKey<Item>, ArrayList<Item>> tags = new HashMap<>();

    public static void addToTag(TagKey<Item> tag, Item item) {
        tags.computeIfAbsent(tag, k -> new ArrayList<>()).add(item);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        ModItems.data.forEach((id, data) -> data.tags.forEach(tag -> addToTag(tag, data.get())));

        tags.forEach((tag, blocks) -> {
            var tagBuilder = getOrCreateTagBuilder(tag);
            blocks.forEach(block -> tagBuilder.add(block));
        });
    }
}
