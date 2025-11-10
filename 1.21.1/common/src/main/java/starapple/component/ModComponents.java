package starapple.component;

import starapple.ModEntry;
import net.minecraft.component.ComponentType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.GlobalPos;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public class ModComponents {

    public static Map<Identifier, ComponentType<?>> data = new HashMap<>();

    public static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        var result = ((ComponentType.Builder) builderOperator.apply(ComponentType.builder())).build();
        data.put(Identifier.of(ModEntry.MOD_ID, id.toLowerCase()), result);
        return result;
    }

    public static void init() {

    }
}
