package me.paulhobbel.discordrp.api.rpc;

import com.sun.jna.Callback;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DiscordEventHandlers extends Structure {

    public interface OnReady extends Callback {
        void accept(DiscordUser user);
    }

    public interface OnStatus extends Callback {
        void accept(int errorCode, String message);
    }

    public interface OnGameUpdate extends Callback {
        void accept(String joinSecret);
    }

    public interface OnJoinRequest extends Callback {
        void accept(DiscordUser user);
    }

    public OnReady ready;
    public OnStatus disconnected;
    public OnStatus errored;
    public OnGameUpdate joinGame;
    public OnGameUpdate spectateGame;
    public OnJoinRequest joinRequest;

    private static final List<String> FIELD_ORDER = Collections.unmodifiableList(Arrays.asList(
            "ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest"));

    @Override
    protected List<String> getFieldOrder() {
        return FIELD_ORDER;
    }
}
