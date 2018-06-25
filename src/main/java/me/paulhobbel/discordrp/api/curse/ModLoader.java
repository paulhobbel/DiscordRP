package me.paulhobbel.discordrp.api.curse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModLoader {
    @Expose
    @SerializedName("id")
    public String id;

    @Expose
    @SerializedName("primary")
    public boolean primary;
}
