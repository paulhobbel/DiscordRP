package me.paulhobbel.discordrp.api.rpc;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DiscordRichPresence extends Structure {

    public String state;
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

    public String matchSecret;
    public String joinSecret;
    public String spectateSecret;

    public byte instance;

    private static final List<String> FIELD_ORDER = Collections.unmodifiableList(Arrays.asList(
            "state",
            "details",
            "startTimestamp",
            "endTimestamp",
            "largeImageKey",
            "largeImageText",
            "smallImageKey",
            "smallImageText",
            "partyId",
            "partySize",
            "partyMax",
            "matchSecret",
            "joinSecret",
            "spectateSecret",
            "instance"
    ));

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
        private String state;
        private String details;

        private long startTimestamp;
        private long endTimestamp;

        private String largeImageKey;
        private String largeImageText;

        private String smallImageKey;
        private String smallImageText;

        private String partyId;
        private int partySize;
        private int partyMax;

        private String matchSecret;
        private String joinSecret;
        private String spectateSecret;

        private byte instance;

        public BaseBuilder() {

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
