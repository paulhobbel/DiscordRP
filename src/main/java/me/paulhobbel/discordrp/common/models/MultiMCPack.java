package me.paulhobbel.discordrp.common.models;

import com.google.gson.annotations.SerializedName;
import me.paulhobbel.discordrp.api.IDiscordRPPack;

import javax.annotation.Nonnull;

public class MultiMCPack implements IDiscordRPPack {

    @SerializedName("packName")
    private String name;

    @SerializedName("packId")
    private String packId;

    @Nonnull
    @Override
    public String getName() {
        return name;
    }

    @Nonnull
    @Override
    public String getPackId() {
        return packId;
    }
}
