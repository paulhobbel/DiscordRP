package me.paulhobbel.discordrp.api.rpc;

public enum DiscordReply {
    /**
     * Used to decline a request via {@link DiscordRPC#Respond(String, DiscordReply)}
     */
    NO,

    /**
     * Used to accept a request via {@link DiscordRPC#Respond(String, DiscordReply)}
     */
    YES,

    /**
     * Unused response, will be treated like {@link #NO}
     * @see #NO
     */
    IGNORE
}