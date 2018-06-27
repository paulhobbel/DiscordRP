package me.paulhobbel.discordrp.api.dimension;

import net.minecraft.world.World;

import java.util.function.Predicate;

public class WorldIdPredicate implements Predicate<World> {
    private final int id;

    public WorldIdPredicate(int id) {
        this.id = id;
    }

    @Override
    public boolean test(World world) {
        return world.provider.getDimension() == id;
    }
}
