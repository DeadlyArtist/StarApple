package starapple.utils;

public interface ILoader {
    public static enum LoaderType {
        Fabric,
        Forge,
        NeoForge,
    }

    public LoaderType getLoaderType();
    public EnvType getEnv();

    public boolean isModLoaded(String id);
}
