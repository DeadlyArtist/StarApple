package starapple.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.HashMap;
import java.util.Map;

public class ModItems {

    public static Map<Identifier, ItemData> data = new HashMap<>();

    public static ItemData STAR_APPLE = register(ItemData.create("STAR_APPLE", () -> new Item(new Item.Settings().rarity(Rarity.RARE).food(ModFoodComponents.STAR_APPLE.get()))).addGroup(ItemGroups.FOOD_AND_DRINK));
    public static ItemData ENCHANTED_STAR_APPLE = register(ItemData.create("ENCHANTED_STAR_APPLE", () -> new Item(new Item.Settings().rarity(Rarity.EPIC).component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true).food(ModFoodComponents.ENCHANTED_STAR_APPLE.get()))).setModel((modelGenerator, self) -> modelGenerator.register(self, ModItems.STAR_APPLE.get(), Models.GENERATED)).addGroup(ItemGroups.FOOD_AND_DRINK));

    public static ItemData register(ItemData.Builder builder) {
        var item = builder.build();
        data.put(item.id, item);
        return item;
    }

    public static void init() {

    }
}
