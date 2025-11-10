package starapple.utils;

public class Loader {
    public static ILoader _impl;

    public static ILoader.LoaderType getLoaderType() {
        return _impl.getLoaderType();
    }

    public static boolean isFabric() {
        return getLoaderType() == ILoader.LoaderType.Fabric;
    }

    public static boolean isForge() {
        return getLoaderType() == ILoader.LoaderType.Forge;
    }

    public static boolean isNeoForge() {
        return getLoaderType() == ILoader.LoaderType.NeoForge;
    }

    public static boolean isModLoaded(String id) {
        return _impl.isModLoaded(id);
    }

    public static EnvType getEnv() {
        return _impl.getEnv();
    }

    public static boolean isClient() {
        return getEnv() == EnvType.CLIENT;
    }

    public static boolean isServer() {
        return getEnv() == EnvType.SERVER;
    }
}
