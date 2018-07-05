package me.paulhobbel.discordrp.proxy;

import me.paulhobbel.discordrp.api.rpc.DiscordEventHandlers;
import me.paulhobbel.discordrp.api.rpc.DiscordRPC;
import me.paulhobbel.discordrp.api.rpc.DiscordRPCHandler;
import me.paulhobbel.discordrp.utils.MinecraftRichPresence;
import me.paulhobbel.discordrp.manager.DiscordAssetManager;
import me.paulhobbel.discordrp.config.DiscordRPConfig;
import me.paulhobbel.discordrp.manager.ManifestManager;
import net.minecraftforge.fml.common.event.*;

public class ClientProxy extends CommonProxy {

    private MinecraftRichPresence presence;

    @Override
    public void onConstruction(FMLConstructionEvent event) {
        super.onConstruction(event);

        DiscordAssetManager.loadAssets();
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
        super.onPreInit(event);

        presence = presence.buildUpon()
                .state("FML PreInitialization")
                .build();

        presence.setPresence();
    }

    @Override
    public void onInit(FMLInitializationEvent event) {
        super.onInit(event);

        presence = presence.buildUpon()
                .state("FML Initialization")
                .build();

        presence.setPresence();
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event) {
        super.onPostInit(event);

        presence = presence.buildUpon()
                .state("FML PostInitialization")
                .build();

        presence.setPresence();
    }

    @Override
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        super.onLoadComplete(event);

        presence = new MinecraftRichPresence.Builder()
                .details("In the Main Menu")
                .startTimestamp(System.currentTimeMillis() / 1000)
                .manifest(ManifestManager.getManifest())
                .build();

        presence.setPresence();
    }
}
