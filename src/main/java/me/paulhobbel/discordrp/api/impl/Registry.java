package me.paulhobbel.discordrp.api.impl;

import me.paulhobbel.discordrp.api.IDiscordRPDimension;
import me.paulhobbel.discordrp.api.IDiscordRPRegistry;
import net.minecraft.world.World;

import java.util.LinkedHashMap;
import java.util.Optional;

public class Registry implements IDiscordRPRegistry {

    private static Registry instance;

    private final LinkedHashMap<String, IDiscordRPDimension> dimensionMap = new LinkedHashMap<>();

    public static final Dimension DEFAULT_DIMENSION = new Dimension("default", "Unknown Dimension");

    @Override
    public void registerDimension(IDiscordRPDimension dimension, String key) {
        dimensionMap.put(key, dimension);
    }

    @Override
    public Optional<IDiscordRPDimension> getDimension(World world) {
        return Optional.ofNullable(dimensionMap.get(world.provider.getDimensionType().getName()));
        //return dimensionMap.get(world.provider.getDimensionType().getName());
    }

    public static Registry getInstance() {
        if(instance == null) instance = new Registry();
        return instance;
    }
}
