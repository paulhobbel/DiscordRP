package me.paulhobbel.discordrp.common.init;

import com.google.gson.Gson;
import me.paulhobbel.discordrp.common.config.DiscordRPConfig;
import me.paulhobbel.discordrp.common.models.ConfigManifest;
import me.paulhobbel.discordrp.common.models.CurseManifest;
import me.paulhobbel.discordrp.common.models.DefaultManifest;
import me.paulhobbel.discordrp.common.models.IManifest;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ManifestManager {
    private static IManifest manifest;

    public static void loadManifest() {
        if(!DiscordRPConfig.manifest.name.isEmpty()) {
            manifest = new ConfigManifest();
            return;
        }

        try {
            manifest = new Gson().fromJson(FileUtils.readFileToString(
                    new File("manifest.json"), StandardCharsets.UTF_8), CurseManifest.class);

        } catch (IOException e) {
            e.printStackTrace();
            manifest = new DefaultManifest();
        }
    }

    public static IManifest getManifest() {
        return manifest;
    }
}
