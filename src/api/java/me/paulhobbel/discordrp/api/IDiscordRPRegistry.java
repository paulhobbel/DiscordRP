package me.paulhobbel.discordrp.api;

import net.minecraft.world.World;

public interface IDiscordRPRegistry {
    void registerDimension(IDiscordRPDimension dimension, String key);

    IDiscordRPDimension getDimension(World world);
}
