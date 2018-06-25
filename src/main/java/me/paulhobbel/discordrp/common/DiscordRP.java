package me.paulhobbel.discordrp.common;

import me.paulhobbel.discordrp.api.rpc.DiscordEventHandlers;
import me.paulhobbel.discordrp.api.rpc.DiscordRPC;
import me.paulhobbel.discordrp.api.curse.Manifest;

import me.paulhobbel.discordrp.api.rpc.DiscordRPCHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@Mod(modid = DiscordRP.MODID, name = DiscordRP.NAME, version = DiscordRP.VERSION, clientSideOnly = true)
public class DiscordRP {
    public static final String MODID = "discordrp";
    public static final String NAME = "Discord Rich Presence";
    public static final String VERSION = "$version";

    public static Manifest MANIFEST;

    public static Logger logger = LogManager.getLogger("DiscordRP");

    static {
        try {
            logger.info(Configuration.manifest.projectId);

            if(!Configuration.manifest.projectId.isEmpty() && !Configuration.manifest.name.isEmpty()) {
                MANIFEST = new Manifest();
                MANIFEST.name = Configuration.manifest.name;
                MANIFEST.projectId = Configuration.manifest.projectId;
            } else {
                MANIFEST = Manifest.loadManifest();
            }
        } catch (IOException e) {
            logger.error("An error occurred while loading the manifest file, you can ignore this error", e);
        }
    }

    private ModpackRichPresence currentPresence;

    @Mod.EventHandler
    public void onConstruction(FMLConstructionEvent event) {
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = user -> logger.info("Ready, user: " + user);

        DiscordRPC.Initialize(!Configuration.applicationId.isEmpty() ? Configuration.applicationId : "460129247239864330", handlers);
        DiscordRPCHandler.start();

        currentPresence = new ModpackRichPresence.Builder()
                .state("FML Construction")
                .details("Loading Minecraft")
                .startTimestamp(System.currentTimeMillis() / 1000)
                .currentModpack()
                .build();

        currentPresence.setPresence();
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        currentPresence = currentPresence.buildUpon()
                .state("FML PreInitialization")
                .build();

        currentPresence.setPresence();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        currentPresence = currentPresence.buildUpon()
                .state("FML Initialization")
                .build();

        currentPresence.setPresence();
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        currentPresence = currentPresence.buildUpon()
                .state("FML PostInitialization")
                .build();

        currentPresence.setPresence();
    }

    @Mod.EventHandler
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        currentPresence = new ModpackRichPresence.Builder()
                .details("In the Main Menu")
                .startTimestamp(System.currentTimeMillis() / 1000)
                .currentModpack()
                .build();

        currentPresence.setPresence();
    }
}
