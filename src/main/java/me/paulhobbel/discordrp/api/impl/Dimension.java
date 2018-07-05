package me.paulhobbel.discordrp.api.impl;

import me.paulhobbel.discordrp.api.IDiscordRPDimension;

public class Dimension implements IDiscordRPDimension {
    private final String assetKey;
    private final String assetText;
    private final String title;

    /**
     *
     * @param assetKey
     * @param title
     */
    public Dimension(String assetKey, String title) {
        this(assetKey, title, title);
    }

    /**
     *
     * @param assetKey
     * @param assetText
     * @param title
     */
    public Dimension(String assetKey, String assetText, String title) {
        this.assetKey = assetKey;
        this.assetText = assetText;
        this.title = title;
    }

    @Override
    public String getAssetKey() {
        return assetKey;
    }

    @Override
    public String getAssetText() {
        return assetText;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
