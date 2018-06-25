package me.paulhobbel.discordrp.common.handler;

import me.paulhobbel.discordrp.api.Dimension;
import me.paulhobbel.discordrp.common.DiscordRP;
import me.paulhobbel.discordrp.common.ModpackRichPresence;
import me.paulhobbel.discordrp.common.registry.DimensionRegistry;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = DiscordRP.MODID, value = Side.CLIENT)
public class GameHandler {

    private static ModpackRichPresence presence;

    @SubscribeEvent
    public static void onClientConnected(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        presence = new ModpackRichPresence.Builder()
                .details("Connecting")
                .state(String.format("Playing %s", event.isLocal() ? "Singleplayer" : "Multiplayer"))
                .startTimestamp(System.currentTimeMillis() / 1000)
                .currentModpack()
                .build();

        presence.setPresence();
    }

    @SubscribeEvent
    public static void onJoinWorld(EntityJoinWorldEvent event) {
        if(event.getEntity().equals(Minecraft.getMinecraft().player)) {
            Dimension dimension = DimensionRegistry.getDimension(event.getWorld());

            presence = presence.buildUpon()
                    .dimension(dimension)
                    .build();

            presence.setPresence();
        }
    }

    @SubscribeEvent
    public static void onClientDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        presence = new ModpackRichPresence.Builder()
                .details("In the Main Menu")
                .startTimestamp(System.currentTimeMillis() / 1000)
                .currentModpack()
                .build();

        presence.setPresence();
    }
}
