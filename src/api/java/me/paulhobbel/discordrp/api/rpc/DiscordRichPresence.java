package me.paulhobbel.discordrp.api.rpc;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Struct wrapper for RichPresence
 *
 * <pre>{@code
 * typedef struct DiscordRichPresence {
 *     const char* state; // max 128 bytes
 *     const char* details; // max 128 bytes
 *     int64_t startTimestamp;
 *     int64_t endTimestamp;
 *     const char* largeImageKey; // max 32 bytes
 *     const char* largeImageText; // max 128 bytes
 *     const char* smallImageKey; // max 32 bytes
 *     const char* smallImageText; // max 128 bytes
 *     const char* partyId; // max 128 bytes
 *     int partySize;
 *     int partyMax;
 *     const char* matchSecret; // max 128 bytes
 *     const char* joinSecret; // max 128 bytes
 *     const char* spectateSecret; // max 128 bytes
 *     int8_t instance;
 * } DiscordRichPresence;
 * }</pre>
 */
public class DiscordRichPresence extends Structure {

    /**
     * The user's current party status
     */
    public String state;

    /**
     * What the player is currently doing
     */
    public String details;

    public long startTimestamp;
    public long endTimestamp;

    public String largeImageKey;
    public String largeImageText;

    public String smallImageKey;
    public String smallImageText;

    public String partyId;
    public int partySize;
    public int partyMax;

    /**
     * @Deprecated
     */
    public String matchSecret;
    public String joinSecret;
    public String spectateSecret;

    /**
     * @Deprecated
     */
    public byte instance;

    private static final List<String> FIELD_ORDER = Collections.unmodifiableList(Arrays.asList(
            "state", "details", "startTimestamp", "endTimestamp", "largeImageKey", "largeImageText", "smallImageKey",
            "smallImageText", "partyId", "partySize", "partyMax", "matchSecret", "joinSecret", "spectateSecret", "instance"));

    public DiscordRichPresence(BaseBuilder<?, ?> builder) {
        state = builder.state;
        details = builder.details;
        startTimestamp = builder.startTimestamp;
        endTimestamp = builder.endTimestamp;
        largeImageKey = builder.largeImageKey;
        largeImageText = builder.largeImageText;
        smallImageKey = builder.smallImageKey;
        smallImageText = builder.smallImageText;
        partyId = builder.partyId;
        partySize = builder.partySize;
        partyMax = builder.partyMax;
        matchSecret = builder.matchSecret;
        joinSecret = builder.joinSecret;
        spectateSecret = builder.spectateSecret;
        instance = builder.instance;
    }

    @Override
    protected List<String> getFieldOrder() {
        return FIELD_ORDER;
    }

    public void setPresence() {
        DiscordRPC.UpdatePresence(this);
    }

    public BaseBuilder buildUpon() {
        return new Builder(this);
    }

    public static class Builder extends BaseBuilder<Builder, DiscordRichPresence> {

        Builder(DiscordRichPresence parent) {
            super(parent);
        }

        @Override
        public DiscordRichPresence build() {
            return new DiscordRichPresence(this);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }

    public abstract static class BaseBuilder<T extends BaseBuilder<T, R>, R extends DiscordRichPresence> {
        protected String state;
        protected String details;

        protected long startTimestamp;
        protected long endTimestamp;

        protected String largeImageKey;
        protected String largeImageText;

        protected String smallImageKey;
        protected String smallImageText;

        protected String partyId;
        protected int partySize;
        protected int partyMax;

        protected String matchSecret;
        protected String joinSecret;
        protected String spectateSecret;

        protected byte instance;

        protected BaseBuilder() {

        }

        protected BaseBuilder(DiscordRichPresence parent) {
            state = parent.state;
            details = parent.details;
            startTimestamp = parent.startTimestamp;
            endTimestamp = parent.endTimestamp;
            largeImageKey = parent.largeImageKey;
            largeImageText = parent.largeImageText;
            smallImageKey = parent.smallImageKey;
            smallImageText = parent.smallImageText;
            partyId = parent.partyId;
            partySize = parent.partySize;
            partyMax = parent.partyMax;
            matchSecret = parent.matchSecret;
            joinSecret = parent.joinSecret;
            spectateSecret = parent.spectateSecret;
            instance = parent.instance;
        }

        public T state(String state) {
            this.state = state;
            return getThis();
        }

        public T details(String details) {
            this.details = details;
            return getThis();
        }

        public T startTimestamp(long startTimestamp) {
            this.startTimestamp = startTimestamp;
            return getThis();
        }

        public T endTimestamp(long endTimestamp) {
            this.endTimestamp = endTimestamp;
            return getThis();
        }

        public T largeImageKey(String largeImageKey) {
            return getThis();
        }

        public abstract R build();

        protected abstract T getThis();
    }
}
