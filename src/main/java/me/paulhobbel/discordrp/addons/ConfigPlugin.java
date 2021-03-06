package me.paulhobbel.discordrp.addons;

import me.paulhobbel.discordrp.DiscordRP;
import me.paulhobbel.discordrp.api.DiscordRPPlugin;
import me.paulhobbel.discordrp.api.IDiscordRPPlugin;
import me.paulhobbel.discordrp.api.IDiscordRPRegistry;
import me.paulhobbel.discordrp.api.impl.Dimension;
import me.paulhobbel.discordrp.common.config.DiscordRPConfig;
import me.paulhobbel.discordrp.common.Log;

@DiscordRPPlugin(modid = DiscordRP.MODID, priority = -1)
public class ConfigPlugin implements IDiscordRPPlugin {
    @Override
    public void register(IDiscordRPRegistry registry) {
        for(String stringDimension : DiscordRPConfig.customDimensions) {
            String[] parts = stringDimension.split("\\|");
            if(parts.length == 2) {
                Log.info("Registering config dimension with key '{}' and title '{}'", parts[0], parts[1]);
                registry.registerDimension(new Dimension(parts[0], parts[1]), parts[0]);
            } else {
                Log.error("'{}' is not a correct format for a dimension, use <type>|<title> instead.", stringDimension);
            }
        }
    }
}
