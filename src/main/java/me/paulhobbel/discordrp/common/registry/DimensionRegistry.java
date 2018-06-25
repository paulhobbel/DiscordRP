package me.paulhobbel.discordrp.common.registry;

import me.paulhobbel.discordrp.api.Dimension;
import me.paulhobbel.discordrp.common.DiscordRP;
import me.paulhobbel.discordrp.common.init.ModDimensions;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryInternal;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = DiscordRP.MODID)
public class DimensionRegistry {
    private static IForgeRegistry<Dimension> REGISTRY;

    @SubscribeEvent
    public static void registerRegistry(RegistryEvent.NewRegistry event) {
        REGISTRY = new RegistryBuilder<Dimension>()
                .setName(new ResourceLocation(DiscordRP.MODID, "dimensions"))
                .setType(Dimension.class)
                .addCallback(new RegistryCallbacks())
                .create();
    }

    public static Dimension getDimension(World world) {
        if(REGISTRY == null) throw new RuntimeException("Registry was not loaded yet");

        return REGISTRY.getValuesCollection().stream()
                .filter(dimension -> dimension.getPredicate() != null)
                .filter(dimension -> dimension.getPredicate().test(world))
                .findFirst()
                .orElse(ModDimensions.UNKNOWN);
    }

    private static class RegistryCallbacks implements IForgeRegistry.AddCallback<Dimension> {
        @Override
        public void onAdd(IForgeRegistryInternal<Dimension> owner, RegistryManager stage, int id, Dimension obj, @Nullable Dimension oldObj) {
            DiscordRP.logger.info("Registered new dimension: " + obj.getKey());
            DiscordRP.logger.info("Entries: " + owner.getEntries());
        }
    }
}
