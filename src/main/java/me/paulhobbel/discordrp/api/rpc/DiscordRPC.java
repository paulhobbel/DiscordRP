package me.paulhobbel.discordrp.api.rpc;

import com.sun.jna.Library;
import com.sun.jna.Native;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DiscordRPC {
    private static DiscordDLL INSTANCE = Native.loadLibrary("discord-rpc", DiscordDLL.class);

    /**
     * Initializes the RPC connection.
     *
     * After running this method it is recommended
     * to start the DiscordRPCHandler with {@link DiscordRPCHandler#start()}
     *
     * @param appId The application id
     * @param handlers The event handler
     */
    public static void Initialize(String appId, @Nullable DiscordEventHandlers handlers) {
        INSTANCE.Discord_Initialize(appId, handlers, true, null);
    }

    public static void Shutdown() {
        INSTANCE.Discord_Shutdown();
    }

    public static void RunCallbacks() {
        INSTANCE.Discord_RunCallbacks();
    }

    public static void UpdateConnection() {
        INSTANCE.Discord_UpdateConnection();
    }

    public static void UpdatePresence(DiscordRichPresence presence) {
        INSTANCE.Discord_UpdatePresence(presence);
    }

    public static void ClearPresence() {
        INSTANCE.Discord_ClearPresence();
    }

    public static void Respond(String userId, @Nonnull DiscordReply reply) {
        INSTANCE.Discord_Respond(userId, reply.ordinal());
    }

    public static void UpdateHandlers(DiscordEventHandlers handlers) {
        INSTANCE.Discord_UpdateHandlers(handlers);
    }

    public enum DiscordReply {
        /**
         * Used to decline a request via {@link #Respond(String, DiscordReply)}
         */
        NO,

        /**
         * Used to accept a request via {@link #Respond(String, DiscordReply)}
         */
        YES,

        /**
         * Unused response, will be treated like {@link #NO}
         * @see #NO
         */
        IGNORE
    }

    private interface DiscordDLL extends Library {
        void Discord_Initialize(@Nonnull String appId, @Nullable DiscordEventHandlers handlers, boolean autoRegister,
                                              @Nullable String steamId);

        void Discord_Shutdown();

        void Discord_RunCallbacks();

        void Discord_UpdateConnection();

        void Discord_UpdatePresence(@Nullable DiscordRichPresence presence);

        void Discord_ClearPresence();

        void Discord_Respond(String userId, int reply);

        void Discord_UpdateHandlers(@Nullable DiscordEventHandlers handlers);
    }
}
