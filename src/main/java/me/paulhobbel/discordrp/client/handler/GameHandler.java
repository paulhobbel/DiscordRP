package me.paulhobbel.discordrp.client.handler;

import me.paulhobbel.discordrp.api.dimension.IDimension;
import me.paulhobbel.discordrp.client.DiscordRP;
import me.paulhobbel.discordrp.common.MinecraftRichPresence;
import me.paulhobbel.discordrp.common.init.ManifestManager;
import me.paulhobbel.discordrp.common.registry.DimensionRegistry;
import net.minecraft.client.Minecraft;
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
                .details("Connecting")
                .state(String.format("Playing %s", event.isLocal() ? "Singleplayer" : "Multiplayer"))
                .startTimestamp(System.currentTimeMillis() / 1000)
                .manifest(ManifestManager.getManifest())
                .build();

        presence.setPresence();
    }

    @SubscribeEvent
    public static void onJoinWorld(EntityJoinWorldEvent event) {
        if(event.getEntity().equals(Minecraft.getMinecraft().player)) {
            IDimension dimension = DimensionRegistry.get(event.getWorld());

            presence = presence.buildUpon()
                    .dimension(dimension)
                    .build();

            presence.setPresence();
        }
    }

    @SubscribeEvent
    public static void onClientDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        presence = new MinecraftRichPresence.Builder()
                .details("In the Main Menu")
                .startTimestamp(System.currentTimeMillis() / 1000)
                .manifest(ManifestManager.getManifest())
                .build();

        presence.setPresence();
    }
}
