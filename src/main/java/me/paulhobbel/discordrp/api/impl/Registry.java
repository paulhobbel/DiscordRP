package me.paulhobbel.discordrp.api.impl;

import me.paulhobbel.discordrp.api.IDiscordRPDimension;
import me.paulhobbel.discordrp.api.IDiscordRPRegistry;
import net.minecraft.world.World;

import java.util.LinkedHashMap;

public class Registry implements IDiscordRPRegistry {

    private static Registry instance;

    private final LinkedHashMap<String, IDiscordRPDimension> dimensionMap = new LinkedHashMap<>();

    @Override
    public void registerDimension(IDiscordRPDimension dimension, String key) {
        dimensionMap.put(key, dimension);
    }

    @Override
    public IDiscordRPDimension getDimension(World world) {
        return dimensionMap.get(world.provider.getDimensionType().getName());
    }

    public static Registry getInstance() {
        if(instance == null) instance = new Registry();
        return instance;
    }
}
