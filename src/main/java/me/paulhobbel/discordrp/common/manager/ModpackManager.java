package me.paulhobbel.discordrp.common.manager;

import me.paulhobbel.discordrp.common.Configuration;
import me.paulhobbel.discordrp.common.Modpack;
import me.paulhobbel.discordrp.common.DiscordRP;
import net.minecraft.client.Minecraft;

public class ModpackManager {
    private static Modpack NONE = new Modpack("default", "Minecraft " + Minecraft.getMinecraft().getVersion());

    public static Modpack getModpack() {
        if(!Configuration.manifest.name.isEmpty()) {
            return new Modpack(!Configuration.manifest.projectId.isEmpty() ? Configuration.manifest.projectId : "default", Configuration.manifest.name);
        }

        if(DiscordRP.MANIFEST == null) {
            return NONE;
        }

        return new Modpack(DiscordRP.MANIFEST.projectId, DiscordRP.MANIFEST.name);
    }
}
