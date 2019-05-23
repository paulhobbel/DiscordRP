package me.paulhobbel.discordrp.common.models;

import com.google.gson.annotations.SerializedName;
import me.paulhobbel.discordrp.api.IDiscordRPPack;

import javax.annotation.Nonnull;

public class CursePack implements IDiscordRPPack {

    @SerializedName("name")
    private String name;

    @SerializedName("projectID")
    private String projectId;

    @Nonnull
    @Override
    public String getName() {
        return name;
    }

    @Nonnull
    @Override
    public String getPackId() {
        return projectId;
    }
}
