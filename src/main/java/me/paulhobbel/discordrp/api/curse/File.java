package me.paulhobbel.discordrp.api.curse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class File {
    @Expose
    @SerializedName("projectID")
    public int projectId;

    @Expose
    @SerializedName("fileID")
    public int fileId;

    @Expose
    @SerializedName("required")
    public boolean required;
}
