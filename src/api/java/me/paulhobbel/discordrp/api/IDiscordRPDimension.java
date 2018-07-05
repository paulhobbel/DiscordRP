package me.paulhobbel.discordrp.api;

public interface IDiscordRPDimension {
    /**
     * Return's the discord asset key.
     */
    String getAssetKey();

    /**
     * Return's the text to put with the asset.
     */
    default String getAssetText() {
        return getTitle();
    };

    /**
     * Return's the text to put in the RichPresence detail field.
     */
    String getTitle();
}
