package me.paulhobbel.discordrp.api.rpc;

import com.sun.jna.Library;
import com.sun.jna.Native;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DiscordRPC {
    private static DiscordDLL INSTANCE = Native.loadLibrary("discord-rpc", DiscordDLL.class);

    private static DiscordRichPresence currentPresence;

    /**
     * Initializes the RPC connection.
     * <br>
     * After running this method it is recommended
     * to start the DiscordRPCHandler with {@link DiscordRPCHandler#start()}
     *
     * @param appId The application id
     * @param handlers The event handler
     */
    public static void Initialize(String appId, @Nullable DiscordEventHandlers handlers) {
        INSTANCE.Discord_Initialize(appId, handlers, false, null);
    }

    /**
     * Shuts down the RPC connection.
     */
    public static void Shutdown() {
        INSTANCE.Discord_Shutdown();
    }

    /**
     * Executes the registered handlers for currently queued events.
     * <br>
     * It is recommended to call this method every 2 seconds, if not
     * called at all, handlers will not receive any events!
     * <br>
     * To run the callbacks automatically you can also use {@link DiscordRPCHandler}
     * this handler will start in it's own thread and run this method in a 2 seconds interval.
     *
     * @see DiscordRPCHandler#start()
     */
    public static void RunCallbacks() {
        INSTANCE.Discord_RunCallbacks();
    }

    public static void UpdateConnection() {
        INSTANCE.Discord_UpdateConnection();
    }

    /**
     * Updates the currently set presence.
     *
     * @param presence The presence to use
     * @see DiscordRichPresence
     */
    public static void UpdatePresence(DiscordRichPresence presence) {
        currentPresence = presence;
        INSTANCE.Discord_UpdatePresence(currentPresence);
    }

    /**
     * Returns the currently set presence.
     *
     * <b>NOTE: </b> This will only return the presence last set by this library
     * @see DiscordRichPresence
     */
    public static DiscordRichPresence CurrentPresence() {
        return currentPresence;
    }

    /**
     * Clear's the currently set presence.
     */
    public static void ClearPresence() {
        INSTANCE.Discord_ClearPresence();
        currentPresence = null;
    }

    /**
     * Responds to the given user with the specified reply type.
     *
     * @param user The user to respond to
     * @param reply The reply type
     * @see DiscordUser
     * @see DiscordReply
     */
    public static void Respond(@Nonnull DiscordUser user, @Nonnull DiscordReply reply) {
        Respond(user.userId, reply);
    }

    /**
     * Responds to the given user with the specified reply type.
     *
     * @param userId The id of the user to respond to
     * @param reply The reply type
     * @see DiscordReply
     */
    public static void Respond(@Nonnull String userId, @Nonnull DiscordReply reply) {
        INSTANCE.Discord_Respond(userId, reply.ordinal());
    }

    /**
     * Updates the registered event handlers to the new provided structure.
     *
     * @param handlers The new handlers struct
     * @see DiscordEventHandlers
     */
    public static void UpdateHandlers(@Nullable DiscordEventHandlers handlers) {
        INSTANCE.Discord_UpdateHandlers(handlers);
    }

    /**
     * Library binding for the official Discord RPC SDK
     */
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
