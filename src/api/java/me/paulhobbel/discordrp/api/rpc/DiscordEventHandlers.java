package me.paulhobbel.discordrp.api.rpc;

import com.sun.jna.Callback;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Struct wrapper for handling RPC events
 *
 * <pre>{@code
 * typedef struct DiscordEventHandlers {
 *     void (*ready)(DiscordUser*);
 *     void (*disconnected)(int errorCode, const char* message);
 *     void (*errored)(int errorCode, const char* message);
 *     void (*joinGame)(const char* joinSecret);
 *     void (*spectateGame)(const char* spectateSecret);
 *     void (*joinRequest)(const DiscordUser* request);
 * } DiscordEventHandlers;
 * }</pre>
 */
public class DiscordEventHandlers extends Structure {

    /**
     * Callback for the ready event
     */
    public interface OnReady extends Callback {
        void accept(DiscordUser user);
    }

    /**
     * Callback for the error en disconnect events
     */
    public interface OnStatus extends Callback {
        void accept(int errorCode, String message);
    }

    /**
     * Callback for game update events such as joinGame and spectateGame
     */
    public interface OnGameUpdate extends Callback {
        void accept(String joinSecret);
    }

    /**
     * Callback for user join request events
     */
    public interface OnJoinRequest extends Callback {
        void accept(DiscordUser user);
    }

    /**
     * Called when RPC connection has been established
     */
    public OnReady ready;

    /**
     * Called when RPC connection has been lost
     */
    public OnStatus disconnected;

    /**
     * Called when an internal error occurs
     */
    public OnStatus errored;

    /**
     * Called when the logged in user joined a game
     */
    public OnGameUpdate joinGame;

    /**
     *
     */
    public OnGameUpdate spectateGame;
    public OnJoinRequest joinRequest;

    private static final List<String> FIELD_ORDER = Collections.unmodifiableList(Arrays.asList(
            "ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest"));

    @Override
    protected List<String> getFieldOrder() {
        return FIELD_ORDER;
    }
}
