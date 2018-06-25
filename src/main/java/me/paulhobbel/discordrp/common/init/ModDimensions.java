package me.paulhobbel.discordrp.common.init;

import me.paulhobbel.discordrp.api.Dimension;
import me.paulhobbel.discordrp.api.world.WorldIdPredicate;
import me.paulhobbel.discordrp.api.world.WorldTypePredicate;
import me.paulhobbel.discordrp.common.DiscordRP;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModDimensions {

    public final static Dimension OVERWORLD = new Dimension("overworld", "In The Overworld", new WorldIdPredicate(0));
    public final static Dimension NETHER = new Dimension("nether", "In The Nether", new WorldIdPredicate(-1));
    public final static Dimension END = new Dimension("end", "In The End", new WorldIdPredicate(1));

    public final static Dimension MINING_WORLD = new Dimension("miningworld", "In The Mining World", new WorldTypePredicate("MiningWorld"));

    public final static Dimension UNKNOWN = new Dimension("unknown", "In an Unknown Dimension", null);

    @Mod.EventBusSubscriber(modid = DiscordRP.MODID)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerDimensions(final RegistryEvent.Register<Dimension> event) {
            final IForgeRegistry<Dimension> registry = event.getRegistry();

            registry.register(OVERWORLD);
            registry.register(NETHER);
            registry.register(END);

            registry.register(MINING_WORLD);
        }
    }
}
