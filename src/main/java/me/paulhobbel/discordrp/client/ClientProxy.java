package me.paulhobbel.discordrp.client;

import me.paulhobbel.discordrp.api.rpc.DiscordEventHandlers;
import me.paulhobbel.discordrp.api.rpc.DiscordRPC;
import me.paulhobbel.discordrp.api.rpc.DiscordRPCHandler;
import me.paulhobbel.discordrp.common.CommonProxy;
import me.paulhobbel.discordrp.common.MinecraftRichPresence;
import me.paulhobbel.discordrp.common.manager.DiscordAssetManager;
import me.paulhobbel.discordrp.common.config.DiscordRPConfig;
import me.paulhobbel.discordrp.common.manager.ManifestManager;
import net.minecraft.client.resources.I18n;
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

        // Translations are not loaded yet here it seems
//        presence = new MinecraftRichPresence.Builder()
//                .state(I18n.format("discordrp.state.construction"))
//                .details(I18n.format("discordrp.loading"))
//                .startTimestamp(System.currentTimeMillis() / 1000)
//                .manifest(ManifestManager.getManifest())
//                .build();
//
//        presence.setPresence();
    }

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {
        super.onPreInit(event);

        presence = new MinecraftRichPresence.Builder()
                .state(I18n.format("discordrp.state.preinit"))
                .details(I18n.format("discordrp.loading"))
                .startTimestamp(System.currentTimeMillis() / 1000)
                .manifest(ManifestManager.getManifest())
                .build();

        presence.setPresence();
    }

    @Override
    public void onInit(FMLInitializationEvent event) {
        super.onInit(event);

        presence = presence.buildUpon()
                .state(I18n.format("discordrp.state.init"))
                .build();

        presence.setPresence();
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event) {
        super.onPostInit(event);

        presence = presence.buildUpon()
                .state(I18n.format("discordrp.state.postinit"))
                .build();

        presence.setPresence();
    }

    @Override
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        super.onLoadComplete(event);

        presence = new MinecraftRichPresence.Builder()
                .details(I18n.format("discordrp.mainmenu"))
                .startTimestamp(System.currentTimeMillis() / 1000)
                .manifest(ManifestManager.getManifest())
                .build();

        presence.setPresence();
    }
}
