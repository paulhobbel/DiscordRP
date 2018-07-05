package me.paulhobbel.discordrp.api;

import net.minecraft.world.World;

import java.util.function.Predicate;

public interface IDiscordRPRegistry {
    void registerDimension(IDiscordRPDimension dimension, Predicate<World> predicate);

    IDiscordRPDimension getDimension(World world);
}
