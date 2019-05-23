package me.paulhobbel.discordrp.core.handlers;

import me.paulhobbel.discordrp.api.IDiscordRPDimension;
import me.paulhobbel.discordrp.api.IDiscordRPPack;
import me.paulhobbel.discordrp.core.OnTickListener;

public class DiscordHandler implements OnTickListener {

    private IDiscordRPPack pack;
    private IDiscordRPDimension dimension;
    private long startTime;

    private boolean shouldUpdate = false;

    public void setPack(IDiscordRPPack pack) {
        this.pack = pack;
        queueUpdate();
    }

    public void setDimension(IDiscordRPDimension dimension) {
        this.dimension = dimension;
        queueUpdate();
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
        queueUpdate();
    }

    public void queueUpdate() {
        shouldUpdate = true;
    }

    @Override
    public void onTick(double elapsedTime) {
        if(shouldUpdate) {



            shouldUpdate = false;
        }
    }
}
