package me.paulhobbel.discordrp.common.asset;

import com.google.gson.annotations.SerializedName;

public class DiscordAsset {

    @SerializedName("type")
    private AssetType type;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    public AssetType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "DiscordAsset{" +
                "type=" + type +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public enum AssetType {
        @SerializedName("1") SMALL,
        @SerializedName("2") LARGE
    }
}
