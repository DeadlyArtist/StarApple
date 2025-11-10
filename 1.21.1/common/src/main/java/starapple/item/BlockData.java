package starapple.item;

import net.minecraft.block.Block;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import starapple.ModEntry;
import starapple.utils.Lazy;
import starapple.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Represents mod block registration metadata.
 */
public class BlockData {
    public final Identifier id;
    public final Supplier<Block> lazy;
    public final Supplier<Item> lazyItem;
    public final Item.Settings itemSettings;
    public final List<RegistryKey<ItemGroup>> groups;
    public final List<TagKey<Block>> tags;
    public final List<TagKey<Item>> itemTags;
    public final String name;
    public final BiConsumer<BlockStateModelGenerator, Block> modelSupplier;
    public BiConsumer<ItemModelGenerator, Item> itemModelSupplier;
    public final BiConsumer<BlockLootTableGenerator, Block> lootSupplier;

    private BlockData(Builder builder) {
        this.id = builder.id;
        this.lazy = new Lazy<>(builder.supplier);
        lazyItem = this::getItem;
        this.itemSettings = builder.itemSettings;
        this.groups = List.copyOf(builder.groups);
        this.tags = List.copyOf(builder.tags);
        this.itemTags = List.copyOf(builder.itemTags);
        this.name = builder.name;
        this.modelSupplier = builder.modelSupplier;
        this.itemModelSupplier = builder.itemModelSupplier;
        this.lootSupplier = builder.lootSupplier;
    }

    public Block get() {
        return lazy.get();
    }

    public Item getItem() {
        return get().asItem();
    }

    // ===== BUILDER =====
    public static Builder create(String id, Supplier<Block> supplier) {
        return new Builder(id, supplier);
    }

    public static class Builder {
        public Identifier id;
        public final Supplier<Block> supplier;
        public Item.Settings itemSettings = new Item.Settings();
        public final List<RegistryKey<ItemGroup>> groups = new ArrayList<>();
        public final List<TagKey<Block>> tags = new ArrayList<>();
        public final List<TagKey<Item>> itemTags = new ArrayList<>();
        public String name;
        public BiConsumer<BlockStateModelGenerator, Block> modelSupplier = (modelGen, self) -> modelGen.registerSimpleCubeAll(self);
        public BiConsumer<ItemModelGenerator, Item> itemModelSupplier = null;
        public BiConsumer<BlockLootTableGenerator, Block> lootSupplier = (lootGen, self) -> lootGen.addDrop(self);

        public Builder(String id, Supplier<Block> supplier) {
            this.id = Identifier.of(ModEntry.MOD_ID, id.toLowerCase());
            this.supplier = supplier;
            this.name = StringUtils.toNormalCase(id);
        }


        public Builder setIdNamespace(String namespace) {
            this.id = Identifier.of(namespace, id.getPath());
            return this;
        }

        public Builder setItemSettings(Item.Settings settings) {
            this.itemSettings = settings;
            return this;
        }

        public Builder addGroup(RegistryKey<ItemGroup> group) {
            this.groups.add(group);
            return this;
        }

        public Builder addTag(TagKey<Block> tag) {
            this.tags.add(tag);
            return this;
        }

        public Builder addItemTag(TagKey<Item> tag) {
            this.itemTags.add(tag);
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setModel(BiConsumer<BlockStateModelGenerator, Block> modelSupplier) {
            this.modelSupplier = modelSupplier;
            return this;
        }

        public Builder setItemModel(BiConsumer<ItemModelGenerator, Item> modelSupplier) {
            this.itemModelSupplier = modelSupplier;
            return this;
        }

        public Builder setDrops(BiConsumer<BlockLootTableGenerator, Block> lootSupplier) {
            this.lootSupplier = lootSupplier;
            return this;
        }

        public BlockData build() {
            return new BlockData(this);
        }
    }
}