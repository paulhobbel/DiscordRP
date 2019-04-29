package me.paulhobbel.discordrp.client.handlers;

import me.paulhobbel.discordrp.DiscordRP;
import me.paulhobbel.discordrp.api.rpc.DiscordEventHandlers;
import me.paulhobbel.discordrp.api.rpc.DiscordRPC;
import me.paulhobbel.discordrp.api.rpc.DiscordRPCHandler;
import me.paulhobbel.discordrp.api.rpc.DiscordReply;
import me.paulhobbel.discordrp.common.Log;
import me.paulhobbel.discordrp.common.config.DiscordRPConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = DiscordRP.MODID, value = Side.CLIENT)
public class DiscordHandler {

    private static ServerData requestedServer;

    public static void loadRPC() {
        DiscordEventHandlers handlers = new DiscordEventHandlers();

        handlers.joinRequest = user -> {
            Log.info("Join request inbound from uid: {}", user.userId);
            DiscordRPC.Respond(user, requestedServer == null ? DiscordReply.YES : DiscordReply.IGNORE);
        };

        handlers.joinGame = secret -> {
            String[] parts = secret.split("-");
            Log.info("Joining game..., secret: {}, id: {}, ip: {}",secret, parts[0], parts[1]);

            requestedServer = new ServerData("", parts[1], false);
        };

        DiscordRPC.Initialize(!DiscordRPConfig.applicationId.isEmpty() ? DiscordRPConfig.applicationId : "460129247239864330", handlers);
        DiscordRPCHandler.start();
    }

    @SubscribeEvent
    public static void onTick(final TickEvent.ClientTickEvent event) {
        Minecraft instance = Minecraft.getMinecraft();

        if(requestedServer != null) {
            try {
                if (instance.player != null) {
                    instance.player.world.sendQuittingDisconnectingPacket();
                    instance.loadWorld(null);
                }

                instance.displayGuiScreen(new GuiConnecting(instance.currentScreen != null ? instance.currentScreen : new GuiMainMenu(), instance, requestedServer));

            } catch (Exception e) {
                Log.error("An error occured while joining", e);
            } finally {
                requestedServer = null;
            }
        }
    }
}
