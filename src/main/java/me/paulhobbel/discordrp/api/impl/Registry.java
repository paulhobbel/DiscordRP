package me.paulhobbel.discordrp.api.impl;

import me.paulhobbel.discordrp.api.IDiscordRPDimension;
import me.paulhobbel.discordrp.api.IDiscordRPRegistry;
import net.minecraft.world.World;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class Registry implements IDiscordRPRegistry {

    private static Registry instance;

    private final LinkedHashMap<Predicate<World>, IDiscordRPDimension> dimensionMap = new LinkedHashMap<>();

    @Override
    public void registerDimension(IDiscordRPDimension dimension, Predicate<World> predicate) {
        dimensionMap.put(predicate, dimension);
    }

    @Override
    public IDiscordRPDimension getDimension(World world) {
        return dimensionMap.entrySet().stream()
                .filter(e -> e.getKey().test(world))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    public static Registry getInstance() {
        if(instance == null) instance = new Registry();
        return instance;
    }
}
