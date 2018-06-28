package me.paulhobbel.discordrp.common.registry;

import me.paulhobbel.discordrp.api.dimension.IDimension;
import me.paulhobbel.discordrp.client.DiscordRP;
import me.paulhobbel.discordrp.common.Log;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class DimensionRegistry {

    private static Map<String, IDimension> dimensions = new HashMap<>();

    public static void register(IDimension dimension) {
        if(!dimensions.containsKey(dimension.getAssetKey())) {
            dimensions.put(dimension.getAssetKey(), dimension);
            Log.info("Registered new dimension: ", dimension.getAssetKey());
        } else {
            Log.warn("Dimension ", dimension.getAssetKey(), " was already registered");
        }
    }

    public static IDimension get(String assetKey) {
        return dimensions.get(assetKey);
    }

    public static IDimension get(World world) {
        return dimensions.values().stream()
                .filter(dimension -> dimension.getPredicate() != null)
                .filter(dimension -> dimension.getPredicate().test(world))
                .findFirst()
                .orElse(IDimension.UNKNOWN);
    }
}
