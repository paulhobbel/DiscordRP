package me.paulhobbel.discordrp.api.dimension;

import net.minecraft.world.World;

import java.util.function.Predicate;

public interface IDimension {
    /**
     * Return's the discord asset key.
     */
    String getAssetKey();

    /**
     * Return's the text to put with the asset.
     */
    String getAssetText();

    /**
     * Return's the text to put in the RichPresence detail field.
     */
    String getTitle();

    /**
     * Predicate whether the given world is this dimension.
     */
    Predicate<World> getPredicate();

    IDimension UNKNOWN = new Impl("default", "In an Unknown Dimension", null);

    class Impl implements IDimension {
        private final String assetKey;
        private final String assetText;
        private final String title;
        private final Predicate<World> predicate;

        /**
         *
         * @param assetKey
         * @param title
         * @param predicate
         */
        public Impl(String assetKey, String title, Predicate<World> predicate) {
            this(assetKey, title, title, predicate);
        }

        /**
         *
         * @param assetKey
         * @param assetText
         * @param title
         * @param predicate
         */
        public Impl(String assetKey, String assetText, String title, Predicate<World> predicate) {
            this.assetKey = assetKey;
            this.assetText = assetText;
            this.title = title;
            this.predicate = predicate;
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

        @Override
        public Predicate<World> getPredicate() {
            return predicate;
        }
    }
}
