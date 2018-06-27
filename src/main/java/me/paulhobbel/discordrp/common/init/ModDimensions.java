package me.paulhobbel.discordrp.common.init;

import me.paulhobbel.discordrp.api.dimension.IDimension;
import me.paulhobbel.discordrp.api.dimension.WorldIdPredicate;
import me.paulhobbel.discordrp.api.dimension.WorldTypePredicate;
import me.paulhobbel.discordrp.common.registry.DimensionRegistry;

public class ModDimensions {

    public final static IDimension OVERWORLD = new IDimension.Impl("overworld", "In The Overworld", new WorldIdPredicate(0));
    public final static IDimension NETHER = new IDimension.Impl("nether", "In The Nether", new WorldIdPredicate(-1));
    public final static IDimension END = new IDimension.Impl("end", "In The End", new WorldIdPredicate(1));

    public final static IDimension MINING_WORLD = new IDimension.Impl("miningworld", "In The Mining World", new WorldTypePredicate("MiningWorld"));

    public static void registerDimensions() {
        DimensionRegistry.register(OVERWORLD);
        DimensionRegistry.register(NETHER);
        DimensionRegistry.register(END);

        DimensionRegistry.register(MINING_WORLD);
    }
}
