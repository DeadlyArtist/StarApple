package starapple.neoforge;

import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffect;
import starapple.ModEntry;
import starapple.component.ModComponents;
import starapple.entity.ModStatusEffects;
import starapple.item.ModBlocks;
import starapple.item.ModItems;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class RegistryInitManagerNeo {
    public static final List<DeferredRegister<?>> all = new ArrayList<>();

    public static final DeferredRegister<ComponentType<?>> DATA_COMPONENT_TYPES = register(DeferredRegister.createDataComponents((RegistryKey<Registry<ComponentType<?>>>) Registries.DATA_COMPONENT_TYPE.getKey(), ModEntry.MOD_ID));
    public static final DeferredRegister<StatusEffect> STATUS_EFFECTS = register(DeferredRegister.create(Registries.STATUS_EFFECT, ModEntry.MOD_ID));
    public static final DeferredRegister<Block> BLOCKS = register(DeferredRegister.createBlocks(ModEntry.MOD_ID));
    public static final DeferredRegister<Item> ITEMS = register(DeferredRegister.createItems(ModEntry.MOD_ID));
    public static final Map<RegistryKey<ItemGroup>, List<Supplier<Item>>> GROUPS = new HashMap<>();

    public static <T> DeferredRegister<T> register(DeferredRegister<T> registry) {
        all.add(registry);
        return registry;
    }

    public static void registerComponents() {
        ModComponents.data.forEach((id, obj) -> {
            Supplier<ComponentType<?>> sup = () -> obj;
            DATA_COMPONENT_TYPES.register(id.getPath(), sup);
        });
    }

    public static void registerStatusEffects() {
        ModStatusEffects.data.forEach((id, obj) -> {
            STATUS_EFFECTS.register(id.getPath(), obj.lazy);
        });
    }

    public static void registerBlocks() {
        ModBlocks.data.forEach((id, obj) -> {
            BLOCKS.register(id.getPath(), obj.lazy);
        });
    }

    public static void registerItems() {
        ModItems.data.forEach((id, obj) -> {
            ITEMS.register(id.getPath(), obj.lazy);
            for (var group : obj.groups) {
                if (!GROUPS.containsKey(group)) GROUPS.put(group, new ArrayList<>());
                GROUPS.get(group).add(obj.lazy);
            }
        });
    }

    public static void init() {
        registerComponents();
        registerStatusEffects();
        registerBlocks();
        registerItems();
    }

    public static void register(IEventBus eventBus) {
        all.forEach(registry -> registry.register(eventBus));
    }

    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        var key = event.getTabKey();
        if (GROUPS.containsKey(key)) {
            for (var item : GROUPS.get(key).reversed()) {
                event.add(item.get());
            }
        }
    }
}
