package me.paulhobbel.discordrp.client.handlers;

import ibxm.Player;
import me.paulhobbel.discordrp.DiscordRP;
import me.paulhobbel.discordrp.api.rpc.DiscordRPC;
import me.paulhobbel.discordrp.api.rpc.DiscordUser;
import me.paulhobbel.discordrp.common.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.server.command.TextComponentHelper;

@Mod.EventBusSubscriber(modid = DiscordRP.MODID, value = Side.CLIENT)
public class ServerHandler {
    private static Minecraft minecraft = Minecraft.getMinecraft();
    private static ServerData queuedServer;

    static void requestConnect(DiscordUser user) {
        ITextComponent acceptBtn = TextComponentHelper.createComponentTranslation(minecraft.player, "discordrp.accept");
        acceptBtn.getStyle()
                .setColor(TextFormatting.GREEN)
                .setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/discordrp accept " + user.userId))
                .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("/discordrp accept " + user.userId)));

        ITextComponent denyBtn = TextComponentHelper.createComponentTranslation(minecraft.player, "discordrp.deny");
        denyBtn.getStyle()
                .setColor(TextFormatting.RED)
                .setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/discordrp deny " + user.userId))
                .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("/discordrp deny " + user.userId)));

        ITextComponent msg = TextComponentHelper.createComponentTranslation(
                minecraft.player, "discordrp.server.request_join", user.username, acceptBtn, denyBtn);

        minecraft.player.sendMessage(msg);

        // TODO: Create a timer to automatically ignore the request after 10 - 30 seconds.
    }

    static void queueConnect(ServerData data) {
        queuedServer = data;
    }

    @SubscribeEvent
    public static void onTick(final TickEvent.ClientTickEvent event) {
        // TODO: Only run this every 
        NetHandlerPlayClient connection = minecraft.getConnection();
        if(connection != null) {
            DiscordRPC.CurrentPresence().partySize = connection.getPlayerInfoMap().size();
            DiscordRPC.CurrentPresence().partyMax = connection.currentServerMaxPlayers;

            DiscordRPC.CurrentPresence().setPresence();
        }

        if(queuedServer != null) {
            try {
                if (minecraft.player != null) {
                    minecraft.player.world.sendQuittingDisconnectingPacket();
                    minecraft.loadWorld(null);
                }

                minecraft.displayGuiScreen(new GuiConnecting(minecraft.currentScreen, minecraft, queuedServer));

            } catch (Exception e) {
                Log.error("An error occured while joining", e);
            } finally {
                queuedServer = null;
            }
        }
    }
}
