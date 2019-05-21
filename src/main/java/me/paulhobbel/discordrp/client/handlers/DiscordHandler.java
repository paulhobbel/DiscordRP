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

    public static void loadRPC() {
        DiscordEventHandlers handlers = new DiscordEventHandlers();

        handlers.joinRequest = user -> {
            Log.info("Join request inbound from uid: {}", user.userId);
            ServerHandler.requestConnect(user);
            //DiscordRPC.Respond(user, requestedServer == null ? DiscordReply.YES : DiscordReply.IGNORE);
        };

        handlers.joinGame = secret -> {
            String[] parts = secret.split("-");
            Log.info("Joining game..., secret: {}, id: {}, ip: {}",secret, parts[0], parts[1]);

            ServerHandler.queueConnect(new ServerData("", parts[1], false));
        };

        handlers.errored = (errorCode, message) -> {
            switch (errorCode) {
                case 4012:
                    Log.error("DiscordRPC Error: no join request for that user, should show this to the user!");
                    break;

                default:
                    Log.warn("DiscordRPC Error: errCode: {}, message: {}", errorCode, message);
            }
        };

        DiscordRPC.Initialize(!DiscordRPConfig.applicationId.isEmpty() ? DiscordRPConfig.applicationId : DiscordRP.DEFAULT_APPID, handlers);
        DiscordRPCHandler.start();
    }
}
