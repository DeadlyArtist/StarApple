package starapple.neoforge.utils;

import starapple.utils.EnvType;
import starapple.utils.ILoader;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.LoadingModList;

public class LoaderImpl implements ILoader {
    @Override
    public ILoader.LoaderType getLoaderType() {
        return ILoader.LoaderType.Fabric;
    }

    @Override
    public boolean isModLoaded(String id) {
        return LoadingModList.get().getModFileById(id) != null;
    }

    @Override
    public EnvType getEnv() {
        return FMLEnvironment.dist.isClient() ? EnvType.CLIENT : EnvType.SERVER;
    }
}