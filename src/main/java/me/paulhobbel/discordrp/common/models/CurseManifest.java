package me.paulhobbel.discordrp.common.models;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nonnull;

public class CurseManifest implements IManifest {

    @SerializedName("name")
    private String name;

    @SerializedName("version")
    private String version;

    @SerializedName("author")
    private String author;

    @SerializedName("projectID")
    private String projectId;

    @Nonnull
    @Override
    public String getName() {
        return name;
    }

    @Nonnull
    @Override
    public String getProjectId() {
        return projectId;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String getAuthor() {
        return author;
    }
}
