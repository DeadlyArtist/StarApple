package starapple;

import starapple.component.ModComponents;
import starapple.item.ModBlocks;
import starapple.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import starapple.entity.ModStatusEffects;

public final class ModEntry {
    public static final String MOD_ID = "starapple";
    public static final Logger _LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        // Write common init code here.
        ModComponents.init();
        ModStatusEffects.init();
        ModBlocks.init();
        ModItems.init();
    }

    public static void preInitialize() {

    }
}
