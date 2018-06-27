package me.paulhobbel.discordrp.common.config;

import me.paulhobbel.discordrp.client.DiscordRP;
import net.minecraftforge.common.config.Config;

@Config(modid = DiscordRP.MODID)
public class DiscordRPConfig {

    @Config.Comment("A custom application id to support your own currentModpack")
    @Config.RequiresMcRestart
    public static String applicationId = "";

    @Config.Comment("Whether to show your elapsed time on Discord")
    public static boolean showTime = true;

    @Config.Comment("Whether to show the version of the modpack you are running")
    public static boolean showVersion = false;

    @Config.Comment("Manually set the manifest of this currentModpack, can be useful if you are not using a curse currentModpack")
    public static ManifestCategory manifest = new ManifestCategory();

    public static class ManifestCategory {
        @Config.Comment("The currentModpack id (used for the Rich Presence Asset. For example 123456 will be referred to asset 'pack_123456')")
        @Config.RequiresMcRestart
        public String projectId = "";

        @Config.Comment("The currentModpack name")
        @Config.RequiresMcRestart
        public String name = "";
    }
}
