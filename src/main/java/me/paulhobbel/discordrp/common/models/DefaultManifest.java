package me.paulhobbel.discordrp.common.models;

import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;

public class DefaultManifest implements IManifest {
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
