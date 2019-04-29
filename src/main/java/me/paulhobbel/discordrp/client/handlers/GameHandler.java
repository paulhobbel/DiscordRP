package me.paulhobbel.discordrp.client.handlers;

import me.paulhobbel.discordrp.api.IDiscordRPDimension;
import me.paulhobbel.discordrp.api.impl.Registry;
import me.paulhobbel.discordrp.DiscordRP;
import me.paulhobbel.discordrp.common.Log;
import me.paulhobbel.discordrp.common.MinecraftRichPresence;
import me.paulhobbel.discordrp.common.manager.ManifestManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = DiscordRP.MODID, value = Side.CLIENT)
public class GameHandler {

    private static MinecraftRichPresence presence;

    @SubscribeEvent
    public static void onClientConnected(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        presence = new MinecraftRichPresence.Builder()
                .details(I18n.format("connect.connecting"))
                .state(I18n.format("discordrp.state.playing", event.isLocal() ? I18n.format("discordrp.singleplayer") : I18n.format("discordrp.multiplayer")))
                .startTimestamp(System.currentTimeMillis() / 1000)
                .manifest(ManifestManager.getManifest())
                .build();

        Minecraft instance = Minecraft.getMinecraft();

        if(!instance.isSingleplayer()) {
            ServerData data = instance.getCurrentServerData();

            if(data != null) {
                Log.info("MULTIPLAYER!, ip: {}", data.serverIP);
                presence.partyId = "Join Server: " + data.serverIP;
                presence.joinSecret = "460129247239864330-" + data.serverIP;
            }
        }

        presence.setPresence();
    }

    @SubscribeEvent
    public static void onJoinWorld(EntityJoinWorldEvent event) {
        if(event.getEntity().equals(Minecraft.getMinecraft().player)) {
            IDiscordRPDimension dimension = Registry.getInstance().getDimension(event.getWorld()).orElse(Registry.DEFAULT_DIMENSION);

            presence = presence.buildUpon()
                    .dimension(dimension)
                    .build();

            presence.setPresence();
        }
    }

    @SubscribeEvent
    public static void onClientDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        presence = new MinecraftRichPresence.Builder()
                .details(I18n.format("discordrp.mainmenu"))
                .startTimestamp(System.currentTimeMillis() / 1000)
                .manifest(ManifestManager.getManifest())
                .build();

        presence.setPresence();
    }
}
