package me.paulhobbel.discordrp.common.models;

import me.paulhobbel.discordrp.api.IDiscordRPPack;
import me.paulhobbel.discordrp.common.config.DiscordRPConfig;

import javax.annotation.Nonnull;

public class ConfigPack implements IDiscordRPPack {
    @Nonnull
    @Override
    public String getName() {
        return DiscordRPConfig.manifest.name;
    }

    @Nonnull
    @Override
    public String getPackId() {
        return !DiscordRPConfig.manifest.projectId.isEmpty() ?
                DiscordRPConfig.manifest.projectId : "default";
    }
}
