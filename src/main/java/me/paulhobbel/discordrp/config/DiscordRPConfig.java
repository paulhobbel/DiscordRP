package me.paulhobbel.discordrp.config;

import me.paulhobbel.discordrp.DiscordRP;
import net.minecraftforge.common.config.Config;

@Config(modid = DiscordRP.MODID)
public class DiscordRPConfig {

    @Config.Comment("A custom application id to support your own modpack")
    @Config.RequiresMcRestart
    public static String applicationId = "";

    @Config.Comment("Whether to show your elapsed time on Discord")
    public static boolean showTime = true;

    public static String[] customDimensions = new String[] { "default|In A Dimension" };

    @Config.Comment("Manually set the manifest of this modpack, can be useful if you are not using a curse modpack")
    public static ManifestCategory manifest = new ManifestCategory();

    public static class ManifestCategory {
        @Config.Comment("The modpack id (used for the Rich Presence Asset. For example 123456 will be referred to asset 'pack_123456')")
        @Config.RequiresMcRestart
        public String projectId = "";

        @Config.Comment("The modpack name")
        @Config.RequiresMcRestart
        public String name = "";
    }
}
