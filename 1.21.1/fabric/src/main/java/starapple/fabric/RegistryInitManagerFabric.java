package starapple.fabric;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import starapple.component.ModComponents;
import starapple.entity.ModStatusEffects;
import starapple.item.ModBlocks;
import starapple.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class RegistryInitManagerFabric {

    public static final Map<RegistryKey<ItemGroup>, List<Supplier<Item>>> GROUPS = new HashMap<>();

    public static void registerComponents() {
        ModComponents.data.forEach((id, obj) -> {
            Registry.register(Registries.DATA_COMPONENT_TYPE, id, obj);
        });
    }

    public static void registerStatusEffects() {
        ModStatusEffects.data.forEach((id, obj) -> {
            Registry.register(Registries.STATUS_EFFECT, id, obj.get());
        });
    }

    public static void registerBlocks() {
        ModBlocks.data.forEach((id, obj) -> {
            Registry.register(Registries.BLOCK, id, obj.get());
        });
    }

    public static void registerItems() {
        ModItems.data.forEach((id, obj) -> {
            Registry.register(Registries.ITEM, id, obj.get());
            for (var group : obj.groups) {
                if (!GROUPS.containsKey(group)) GROUPS.put(group, new ArrayList<>());
                GROUPS.get(group).add(obj.lazy);
            }
        });

        GROUPS.forEach((group, items) -> {
            ItemGroupEvents.modifyEntriesEvent(group).register((itemGroup) -> {
                //itemGroup.addAll(items.stream().map(item -> new ItemStack(item.get())).toList());
                for (var item : items.reversed()) itemGroup.add(item.get());
            });
        });
    }

    public static void init() {
        registerComponents();
        registerStatusEffects();
        registerBlocks();
        registerItems();
    }
}
