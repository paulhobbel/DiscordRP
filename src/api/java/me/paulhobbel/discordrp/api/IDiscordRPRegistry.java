package me.paulhobbel.discordrp.api;

import net.minecraft.world.World;

import java.util.Optional;

public interface IDiscordRPRegistry {
    void registerDimension(IDiscordRPDimension dimension, String key);

    Optional<IDiscordRPDimension> getDimension(World world);
}
