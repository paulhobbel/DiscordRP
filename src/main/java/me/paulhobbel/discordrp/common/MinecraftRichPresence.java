package me.paulhobbel.discordrp.common;

import me.paulhobbel.discordrp.api.dimension.IDimension;
import me.paulhobbel.discordrp.api.rpc.DiscordRichPresence;
import me.paulhobbel.discordrp.common.config.DiscordRPConfig;
import me.paulhobbel.discordrp.common.models.IManifest;
import me.paulhobbel.discordrp.common.registry.DiscordAssetRegistry;
import me.paulhobbel.discordrp.common.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MinecraftRichPresence extends DiscordRichPresence {

    private IDimension dimension;
    private IManifest manifest;

    private String originalDetails;

    public MinecraftRichPresence(Builder builder) {
        super(builder);

        dimension = builder.dimension;
        manifest = builder.manifest;
        originalDetails = builder.originalDetails;

        largeImageKey = "main_menu";

        List<Object> detailParts = new ArrayList<>();

        if(dimension != null) {
            detailParts.add(dimension.getTitle());

            largeImageKey = DiscordAssetRegistry.getKey("dim_" + dimension.getAssetKey());
            largeImageText = dimension.getAssetText();
        } else if(originalDetails != null) {
            detailParts.add(originalDetails);
        }

        if(manifest != null) {
            detailParts.add(manifest.getName());
            if(DiscordAssetRegistry.contains("pack_" + manifest.getProjectId())) {
                smallImageKey = "pack_" + manifest.getProjectId();
            }
            smallImageText = manifest.getName();
        }

        details = StringUtils.join(" | ", detailParts.toArray());
    }

    @Override
    public Builder buildUpon() {
        return new Builder(this);
    }

    public static class Builder extends DiscordRichPresence.BaseBuilder<Builder, DiscordRichPresence> {

        private IDimension dimension;
        private IManifest manifest;

        //private

        private String originalDetails;

        public Builder() {

        }

        Builder(MinecraftRichPresence parent) {
            super(parent);
            dimension = parent.dimension;
            manifest = parent.manifest;
            originalDetails = parent.originalDetails;
        }

        @Override
        public Builder details(String details) {
            originalDetails = details;
            return getThis();
        }

        @Override
        public Builder startTimestamp(long startTimestamp) {
            // TODO: Move this to constructor of MinecraftRichPresence?
            if(DiscordRPConfig.showTime)
                return super.startTimestamp(startTimestamp);

            return super.startTimestamp(0);
        }

        public Builder manifest(IManifest manifest) {
            this.manifest = manifest;
            return getThis();
        }

        public Builder dimension(IDimension dimension) {
            this.dimension = dimension;
            return getThis();
        }

        @Override
        public MinecraftRichPresence build() {
            return new MinecraftRichPresence(this);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}
