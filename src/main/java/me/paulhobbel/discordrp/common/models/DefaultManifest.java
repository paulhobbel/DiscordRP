package me.paulhobbel.discordrp.common.models;

import me.paulhobbel.discordrp.api.IDiscordRPPack;
import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;

public class DefaultManifest implements IDiscordRPPack {
    @Nonnull
    @Override
    public String getName() {
        return "Minecraft";
    }

    @Nonnull
    @Override
    public String getPackId() {
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
