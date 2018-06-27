package me.paulhobbel.discordrp.common.models;

import me.paulhobbel.discordrp.common.config.DiscordRPConfig;

import javax.annotation.Nonnull;

public class ConfigManifest implements IManifest {
    @Nonnull
    @Override
    public String getName() {
        return DiscordRPConfig.manifest.name;
    }

    @Nonnull
    @Override
    public String getProjectId() {
        return !DiscordRPConfig.manifest.projectId.isEmpty() ?
                DiscordRPConfig.manifest.projectId : "default";
    }

    @Override
    public String getVersion() {
        return null;
    }

    @Override
    public String getAuthor() {
        return null;
    }
}
