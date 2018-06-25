package me.paulhobbel.discordrp.api.curse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Manifest {
    @Expose
    @SerializedName("minecraft")
    public Minecraft minecraft;

    @Expose
    @SerializedName("manifestType")
    public String manifestType;

    @Expose
    @SerializedName("manifestVersion")
    public int manifestVersion;

    @Expose
    @SerializedName("name")
    public String name;

    @Expose
    @SerializedName("version")
    public String version;

    @Expose
    @SerializedName("author")
    public String author;

    @Expose
    @SerializedName("projectID")
    public String projectId;

    @Expose
    @SerializedName("files")
    public List<File> files = new ArrayList<>();

    @Expose
    @SerializedName("overrides")
    public String overrides;

    public static Manifest loadManifest() throws IOException {
        Gson gson = new GsonBuilder().create();

        return gson.fromJson(FileUtils.readFileToString(new java.io.File("manifest.json"), StandardCharsets.UTF_8), Manifest.class);
    }
}
