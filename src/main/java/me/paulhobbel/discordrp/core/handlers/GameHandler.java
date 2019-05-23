package me.paulhobbel.discordrp.core.handlers;

import me.paulhobbel.discordrp.DiscordRP;
import me.paulhobbel.discordrp.core.OnTickListener;
import net.minecraft.client.gui.GuiMainMenu;

public class GameHandler implements OnTickListener {
    @Override
    public void onTick(double elapsedTime) {
        if(DiscordRP.instance.currentScreen instanceof GuiMainMenu) {
            // reset rich presence
        }
    }
}
