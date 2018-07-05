package me.paulhobbel.discordrp.api.rpc;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Struct wrapper for a discord user
 *
 * <pre>{@code
 * typedef struct DiscordUser {
 *     const char* userId;
 *     const char* username;
 *     const char* avatar;
 * } DiscordUser;
 * }</pre>
 */
public class DiscordUser extends Structure {

    /**
     * The user id of the discord user
     */
    public String userId;

    /**
     * The username of the discord user
     */
    public String username;

    /**
     * The discriminator of the discord user
     */
    public String discriminator;

    /**
     * The avatar of the discord user
     */
    public String avatar;

    private static final List<String> FIELD_ORDER = Collections.unmodifiableList(Arrays.asList(
            "userId", "username", "discriminator", "avatar"));

    @Override
    protected List<String> getFieldOrder() {
        return FIELD_ORDER;
    }
}
