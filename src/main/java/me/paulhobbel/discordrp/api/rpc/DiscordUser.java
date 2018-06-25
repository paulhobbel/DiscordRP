package me.paulhobbel.discordrp.api.rpc;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DiscordUser extends Structure {

    public String userId;
    public String username;
    public String discriminator;
    public String avatar;

    private static final List<String> FIELD_ORDER = Collections.unmodifiableList(Arrays.asList(
            "userId",
            "username",
            "discriminator",
            "avatar"
    ));

    @Override
    protected List<String> getFieldOrder() {
        return FIELD_ORDER;
    }
}
