package starapple.neoforge;

import starapple.neoforge.utils.LoaderImpl;
import starapple.utils.Loader;
import starapple.utils.qwe;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;

import starapple.ModEntry;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(ModEntry.MOD_ID)
public final class ModEntryNeoForge {
    public ModEntryNeoForge(IEventBus eventBus, ModContainer container) {
        eventBus.register(new EventHandler()); // Only with @SubscribeEvent present

        ModEntry.init();
        RegistryInitManagerNeo.init();

        eventBus.addListener(this::setup);

        qwe.info("Registering " + ModEntry.MOD_ID + " (Common)");
        RegistryInitManagerNeo.register(eventBus);
    }

    public static void preInitialize() {
        Loader._impl = new LoaderImpl();
    }

    public void setup(final FMLCommonSetupEvent event) {

    }

    public static class EventHandler {
        @SubscribeEvent
        public void buildContents(BuildCreativeModeTabContentsEvent event) {
            RegistryInitManagerNeo.buildContents(event);
        }
    }

    @EventBusSubscriber(modid = ModEntry.MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            qwe.info("Registering " + ModEntry.MOD_ID + " (Client)");
        }
    }
}
