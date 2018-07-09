package me.paulhobbel.discordrp.addons;

import me.paulhobbel.discordrp.DiscordRP;
import me.paulhobbel.discordrp.api.DiscordRPPlugin;
import me.paulhobbel.discordrp.api.IDiscordRPPlugin;
import me.paulhobbel.discordrp.api.IDiscordRPRegistry;
import me.paulhobbel.discordrp.api.impl.Dimension;

@DiscordRPPlugin(modid = DiscordRP.MODID)
public class MinecraftPlugin implements IDiscordRPPlugin {
    @Override
    public void register(IDiscordRPRegistry registry) {
        registry.registerDimension(new Dimension("overworld", "Overworld"), "overworld");
        registry.registerDimension(new Dimension("nether", "Nether"), "the_nether");
        registry.registerDimension(new Dimension("end", "End"), "the_end");
    }
}
