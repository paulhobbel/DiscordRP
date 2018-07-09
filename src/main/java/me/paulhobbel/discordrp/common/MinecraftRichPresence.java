package me.paulhobbel.discordrp.common;

import me.paulhobbel.discordrp.api.IDiscordRPDimension;
import me.paulhobbel.discordrp.api.rpc.DiscordRichPresence;
import me.paulhobbel.discordrp.common.manager.DiscordAssetManager;
import me.paulhobbel.discordrp.common.config.DiscordRPConfig;
import me.paulhobbel.discordrp.api.IDiscordRPManifest;
import net.minecraft.client.resources.I18n;

import java.util.ArrayList;
import java.util.List;

public class MinecraftRichPresence extends DiscordRichPresence {

    private IDiscordRPDimension dimension;
    private IDiscordRPManifest manifest;

    private String originalDetails;

    public MinecraftRichPresence(Builder builder) {
        super(builder);

        dimension = builder.dimension;
        manifest = builder.manifest;
        originalDetails = builder.originalDetails;

        largeImageKey = "main_menu";

        List<String> detailParts = new ArrayList<>();

        if(dimension != null) {
            detailParts.add(I18n.format("discordrp.dimension", dimension.getTitle()));

            largeImageKey = DiscordAssetManager.getKey("dim_" + dimension.getAssetKey());
            largeImageText = dimension.getAssetText();
        } else if(originalDetails != null) {
            detailParts.add(originalDetails);
        }

        if(manifest != null) {
            detailParts.add(manifest.getName());
            if(DiscordAssetManager.contains("pack_" + manifest.getProjectId())) {
                smallImageKey = "pack_" + manifest.getProjectId();
            }
            smallImageText = manifest.getName();
        }

        details = String.join(" | ", detailParts);
    }

    @Override
    public Builder buildUpon() {
        return new Builder(this);
    }

    public static class Builder extends DiscordRichPresence.BaseBuilder<Builder, DiscordRichPresence> {

        private IDiscordRPDimension dimension;
        private IDiscordRPManifest manifest;

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

        public Builder manifest(IDiscordRPManifest manifest) {
            this.manifest = manifest;
            return getThis();
        }

        public Builder dimension(IDiscordRPDimension dimension) {
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
