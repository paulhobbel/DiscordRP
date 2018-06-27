package me.paulhobbel.discordrp.api.dimension;

import net.minecraft.world.World;

import java.util.function.Predicate;

public class WorldTypePredicate implements Predicate<World> {

    private final String type;

    public WorldTypePredicate(String type) {
        this.type = type;
    }

    @Override
    public boolean test(World world) {
        return world.provider.getDimensionType().getName().equals(type);
    }
}
