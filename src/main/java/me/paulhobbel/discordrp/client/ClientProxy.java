package me.paulhobbel.discordrp.client;

import me.paulhobbel.discordrp.api.rpc.DiscordEventHandlers;
import me.paulhobbel.discordrp.api.rpc.DiscordRPC;
import me.paulhobbel.discordrp.api.rpc.DiscordRPCHandler;
import me.paulhobbel.discordrp.api.rpc.DiscordReply;
import me.paulhobbel.discordrp.client.commands.DiscordRPCommand;
import me.paulhobbel.discordrp.client.handlers.DiscordHandler;
import me.paulhobbel.discordrp.common.CommonProxy;
import me.paulhobbel.discordrp.common.Log;
import me.paulhobbel.discordrp.common.MinecraftRichPresence;
import me.paulhobbel.discordrp.common.manager.DiscordAssetManager;
import me.paulhobbel.discordrp.common.config.DiscordRPConfig;
import me.paulhobbel.discordrp.common.manager.ManifestManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.event.*;

public class ClientProxy extends CommonProxy {

    private MinecraftRichPresence presence;

    @Override
    public void onConstruction(FMLConstructionEvent event) {
        super.onConstruction(event);

        DiscordAssetManager.loadAssets();
        ManifestManager.loadManifest();
        DiscordHandler.loadRPC();

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

        ClientCommandHandler.instance.registerCommand(new DiscordRPCommand());

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
