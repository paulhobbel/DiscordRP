package me.paulhobbel.discordrp.common.models;

import javax.annotation.Nonnull;

public interface IManifest {
    @Nonnull
    String getName();

    @Nonnull
    String getProjectId();

    String getVersion();

    String getAuthor();
}
