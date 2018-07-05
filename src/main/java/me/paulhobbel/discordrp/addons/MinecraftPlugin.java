package me.paulhobbel.discordrp.addons;

import me.paulhobbel.discordrp.DiscordRP;
import me.paulhobbel.discordrp.api.DiscordRPPlugin;
import me.paulhobbel.discordrp.api.IDiscordRPPlugin;
import me.paulhobbel.discordrp.api.IDiscordRPRegistry;
import me.paulhobbel.discordrp.api.impl.Dimension;
import me.paulhobbel.discordrp.api.world.WorldIdPredicate;

@DiscordRPPlugin(DiscordRP.MODID)
public class MinecraftPlugin implements IDiscordRPPlugin {
    @Override
    public void register(IDiscordRPRegistry registry) {
        registry.registerDimension(new Dimension("overworld", "In The Overworld"), new WorldIdPredicate(0));
        registry.registerDimension(new Dimension("nether", "In The Nether"), new WorldIdPredicate(-1));
        registry.registerDimension(new Dimension("end", "In The End"), new WorldIdPredicate(1));
    }
}
