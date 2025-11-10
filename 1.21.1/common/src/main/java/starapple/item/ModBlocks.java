package starapple.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class ModBlocks {
    public static Map<Identifier, BlockData> data = new HashMap<>();



    public static BlockData register(BlockData.Builder builder) {
        var block = builder.build();
        data.put(block.id, block);
        ModItems.register(ItemData.fromBlock(block));
        return block;
    }

    public static void init() {

    }
}
