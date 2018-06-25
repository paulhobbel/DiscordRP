package me.paulhobbel.discordrp.api;

import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.function.Predicate;

public class Dimension extends IForgeRegistryEntry.Impl<Dimension> {
    private String key;
    private String text;
    private Predicate<World> predicate;

    public Dimension(String key, String text, Predicate<World> predicate) {
        this.key = key;
        this.text = text;
        this.predicate = predicate;
        setRegistryName(key);
    }

    public String getKey() {
        return key;
    }

    public String getText() {
        return text;
    }

    public Predicate<World> getPredicate() {
        return predicate;
    }
}
