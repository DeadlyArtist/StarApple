package starapple.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import starapple.ModEntry;
import starapple.utils.Lazy;
import starapple.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Represents mod item registration metadata
 */
public class ItemData {
    public final Identifier id;
    public final Supplier<Item> lazy;
    public final List<RegistryKey<ItemGroup>> groups;
    public final List<TagKey<Item>> tags;
    public final String name;
    public final BiConsumer<ItemModelGenerator, Item> modelSupplier;

    private ItemData(Builder builder) {
        this.id = builder.id;
        this.lazy = new Lazy<>(builder.supplier);
        this.groups = List.copyOf(builder.groups);
        this.tags = List.copyOf(builder.tags);
        this.name = builder.name;
        this.modelSupplier = builder.modelSupplier;
    }


    public Item get() {
        return lazy.get();
    }

    // ===== BUILDER =====
    public static Builder create(String id, Supplier<Item> itemSupplier) {
        return new Builder(id, itemSupplier);
    }

    public static Builder fromBlock(BlockData block) {
        var builder = new Builder(block.id.getPath(), () -> new BlockItem(block.get(), block.itemSettings))
                .setIdNamespace(block.id.getNamespace())
                .setName(block.name)
                .setModel(block.itemModelSupplier);

        for (var group : block.groups) builder.addGroup(group);
        for (var tag : block.itemTags) builder.addTag(tag);

        return builder;
    }

    public static class Builder {
        public Identifier id;
        public Supplier<Item> supplier;
        public final List<RegistryKey<ItemGroup>> groups = new ArrayList<>();
        public final List<TagKey<Item>> tags = new ArrayList<>();
        public String name;
        public BiConsumer<ItemModelGenerator, Item> modelSupplier = (modelGen, self) -> modelGen.register(self, Models.GENERATED);

        public Builder(String id, Supplier<Item> supplier) {
            this.id = Identifier.of(ModEntry.MOD_ID, id.toLowerCase());
            this.supplier = supplier;
            this.name = StringUtils.toNormalCase(id);
        }

        public Builder setIdNamespace(String namespace) {
            this.id = Identifier.of(namespace, id.getPath());
            return this;
        }

        public Builder supplier(Supplier<Item> supplier) {
            this.supplier = supplier;
            return this;
        }

        public Builder addGroup(RegistryKey<ItemGroup> group) {
            this.groups.add(group);
            return this;
        }

        public Builder addTag(TagKey<Item> tag) {
            this.tags.add(tag);
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setModel(BiConsumer<ItemModelGenerator, Item> supplier) {
            this.modelSupplier = supplier;
            return this;
        }

        public ItemData build() {
            return new ItemData(this);
        }
    }
}