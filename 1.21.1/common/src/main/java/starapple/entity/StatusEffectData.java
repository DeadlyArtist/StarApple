package starapple.entity;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import starapple.ModEntry;
import starapple.utils.Lazy;
import starapple.utils.StatusEffectUtils;
import starapple.utils.StringUtils;

import java.util.function.Supplier;

/**
 * Represents mod status effect registration metadata.
 */
public class StatusEffectData {
    public final Identifier id;
    public final Supplier<StatusEffect> lazy;
    public final String name;

    private StatusEffectData(Builder builder) {
        this.id = builder.id;
        this.lazy = new Lazy<>(builder.supplier);
        this.name = builder.name;
    }

    public StatusEffect get() {
        return lazy.get();
    }

    public RegistryEntry<StatusEffect> getEntry() {
        return StatusEffectUtils.getEntry(get());
    }

    // ===== BUILDER =====
    public static Builder create(String id, Supplier<StatusEffect> supplier) {
        return new Builder(id, supplier);
    }

    public static class Builder {
        public Identifier id;
        public Supplier<StatusEffect> supplier;
        public String name;

        public Builder(String id, Supplier<StatusEffect> supplier) {
            this.id = Identifier.of(ModEntry.MOD_ID, id.toLowerCase());
            this.supplier = supplier;
            this.name = StringUtils.toNormalCase(id);
        }

        public Builder setIdNamespace(String namespace) {
            this.id = Identifier.of(namespace, id.getPath());
            return this;
        }

        public Builder supplier(Supplier<StatusEffect> supplier) {
            this.supplier = supplier;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public StatusEffectData build() {
            return new StatusEffectData(this);
        }
    }
}