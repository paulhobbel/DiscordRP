package me.paulhobbel.discordrp.common.models;

import me.paulhobbel.discordrp.api.IDiscordRPManifest;
import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;

public class DefaultManifest implements IDiscordRPManifest {
    @Nonnull
    @Override
    public String getName() {
        return "Minecraft";
    }

    @Nonnull
    @Override
    public String getProjectId() {
        return "default";
    }

    @Override
    public String getVersion() {
        return Minecraft.getMinecraft().getVersion();
    }

    @Override
    public String getAuthor() {
        return null;
    }
}
