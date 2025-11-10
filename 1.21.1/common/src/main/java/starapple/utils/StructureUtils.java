package starapple.utils;

import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.structure.Structure;

public class StructureUtils {
    public static Pair<BlockPos, RegistryEntry<Structure>> locate(ServerWorld serverWorld, RegistryKey<Structure> structure, BlockPos searchPos, int radius, boolean skipReferencedStructure) {
        return serverWorld.getChunkManager().getChunkGenerator().locateStructure(serverWorld, RegistryEntryList.of(serverWorld.getRegistryManager().get(RegistryKeys.STRUCTURE).getEntry(structure).orElseThrow()), searchPos, radius, skipReferencedStructure);
    }
}
