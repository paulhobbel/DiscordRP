package me.paulhobbel.discordrp.common.manager;

import me.paulhobbel.discordrp.api.IDiscordRPPack;
import me.paulhobbel.discordrp.common.config.DiscordRPConfig;
import me.paulhobbel.discordrp.common.models.ConfigPack;
import me.paulhobbel.discordrp.common.models.CursePack;
import me.paulhobbel.discordrp.common.models.MultiMCPack;
import me.paulhobbel.discordrp.utils.FileUtilities;

import java.io.File;

public class PackManager {

    private static IDiscordRPPack info;
    private static PackType type;

    private static File CURSE_FILE = new File("manifest.json");
    private static File MULTIMC_FILE = new File("instance.json");

    public static boolean loadPack() {
        // Check config first
        if(!DiscordRPConfig.manifest.name.isEmpty()) {
            info = new ConfigPack();
            type = PackType.CUSTOM;
            return true;
        }

        try {
            if (CURSE_FILE.exists()) {
                info = FileUtilities.getJsonFromFile(CURSE_FILE, CursePack.class);
                type = PackType.CURSE;
                return true;
            }

            if (MULTIMC_FILE.exists()) {
                info = FileUtilities.getJsonFromFile(CURSE_FILE, MultiMCPack.class);
                type = PackType.MULTIMC;

                return true;
            }
            // TODO: Load technic pack somehow...

        } catch (Exception ex) {

        }

        return false;
    }

    public static boolean isPack() {
        return info != null;
    }

    public static IDiscordRPPack getPackInfo() {
        return info;
    }

    public static PackType getPackType() {
        return type;
    }

    public enum PackType {
        CURSE, MULTIMC, TECHNIC, CUSTOM
    }
}
