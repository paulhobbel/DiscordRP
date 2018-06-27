package me.paulhobbel.discordrp.client;

import me.paulhobbel.discordrp.api.rpc.DiscordEventHandlers;
import me.paulhobbel.discordrp.api.rpc.DiscordRPC;
import me.paulhobbel.discordrp.api.rpc.DiscordRPCHandler;
import me.paulhobbel.discordrp.common.CommonProxy;
import me.paulhobbel.discordrp.common.MinecraftRichPresence;
import me.paulhobbel.discordrp.common.config.DiscordRPConfig;
import me.paulhobbel.discordrp.common.init.ManifestManager;
import me.paulhobbel.discordrp.common.init.ModDimensions;
import net.minecraftforge.fml.common.event.*;

public class ClientProxy implements CommonProxy {

    private MinecraftRichPresence presence;

    @Override
    public void onConstruction(FMLConstructionEvent event) {
        ManifestManager.loadManifest();

        DiscordEventHandlers handlers = new DiscordEventHandlers();
//        handlers.ready = user -> logger.info("Ready, user: " + user);
//        handlers.disconnected = (errorCode, message) -> logger.info("An error occured, errorCode: " + errorCode + ", message: " + message);
//        handlers.errored = (errorCode, message) -> logger.info("An error occured, errorCode: " + errorCode + ", message: " + message);

        DiscordRPC.Initialize(!DiscordRPConfig.applicationId.isEmpty() ? DiscordRPConfig.applicationId : "460129247239864330", handlers);
        DiscordRPCHandler.start();

        presence = new MinecraftRichPresence.Builder()
                .state("FML Construction")
                .details("Loading Minecraft")
                .startTimestamp(System.currentTimeMillis() / 1000)
                .manifest(ManifestManager.getManifest())
                .build();

        presence.setPresence();
    }

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {
        presence = presence.buildUpon()
                .state("FML PreInitialization")
                .build();

        presence.setPresence();
    }

    @Override
    public void onInit(FMLInitializationEvent event) {
        presence = presence.buildUpon()
                .state("FML Initialization")
                .build();

        presence.setPresence();
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event) {
        ModDimensions.registerDimensions();

        presence = presence.buildUpon()
                .state("FML PostInitialization")
                .build();

        presence.setPresence();
    }

    @Override
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        presence = new MinecraftRichPresence.Builder()
                .details("In the Main Menu")
                .startTimestamp(System.currentTimeMillis() / 1000)
                .manifest(ManifestManager.getManifest())
                .build();

        presence.setPresence();
    }
}
