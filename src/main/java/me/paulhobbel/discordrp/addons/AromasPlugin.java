package me.paulhobbel.discordrp.addons;

import me.paulhobbel.discordrp.api.DiscordRPPlugin;
import me.paulhobbel.discordrp.api.IDiscordRPPlugin;
import me.paulhobbel.discordrp.api.IDiscordRPRegistry;
import me.paulhobbel.discordrp.api.impl.Dimension;

@DiscordRPPlugin(modid = "aroma1997sdimension")
public class AromasPlugin implements IDiscordRPPlugin {
    @Override
    public void register(IDiscordRPRegistry registry) {
        registry.registerDimension(new Dimension("miningworld", "In The Mining World"),"MiningWorld");
    }
}
