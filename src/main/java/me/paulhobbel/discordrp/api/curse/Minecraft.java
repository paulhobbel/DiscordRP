package me.paulhobbel.discordrp.api.curse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Minecraft {
    @Expose
    @SerializedName("version")
    public String version;

    @Expose
    @SerializedName("modLoaders")
    public List<ModLoader> modLoaders = new ArrayList<>();
}
