package me.paulhobbel.discordrp.api;

import javax.annotation.Nonnull;

public interface IDiscordRPManifest {
    @Nonnull
    String getName();

    @Nonnull
    String getProjectId();

    String getVersion();

    String getAuthor();
}
