package me.paulhobbel.discordrp.common;

import me.paulhobbel.discordrp.api.Dimension;
import me.paulhobbel.discordrp.api.rpc.DiscordRichPresence;
import me.paulhobbel.discordrp.common.manager.ModpackManager;

public class ModpackRichPresence extends DiscordRichPresence {

    private Dimension dimension;
    private Modpack modpack;

    private String originalDetails;

    public ModpackRichPresence(Builder builder) {
        super(builder);

        dimension = builder.dimension;
        modpack = builder.modpack;
        originalDetails = builder.originalDetails;
    }

    @Override
    public Builder buildUpon() {
        return new Builder(this);
    }

    public static class Builder extends DiscordRichPresence.BaseBuilder<Builder, DiscordRichPresence> {

        private Dimension dimension;
        private Modpack modpack;

        private String originalDetails;

        public Builder() {

        }

        Builder(ModpackRichPresence parent) {
            super(parent);
            dimension = parent.dimension;
            modpack = parent.modpack;
            originalDetails = parent.originalDetails;
        }

        @Override
        public Builder details(String details) {
            originalDetails = details;
            return getThis();
        }

        public Builder currentModpack() {
            return modpack(ModpackManager.getModpack());
        }

        public Builder modpack(Modpack modpack) {
            this.modpack = modpack;
            return getThis();
        }

        public Builder dimension(Dimension dimension) {
            this.dimension = dimension;
            return getThis();
        }

        @Override
        public ModpackRichPresence build() {
            ModpackRichPresence presence = new ModpackRichPresence(this);

            presence.details = originalDetails;

            presence.largeImageKey = "main_menu";
            if(dimension != null) {
                presence.largeImageKey = "dim_" + dimension.getKey();
                presence.largeImageText = dimension.getText();
                presence.details = dimension.getText();
            }

            if(modpack != null) {
                presence.details += " | " + modpack.getName();
                presence.smallImageKey = "pack_" + modpack.getProjectId();
                presence.smallImageText = modpack.getName();
            }

            return presence;
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}
