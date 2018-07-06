package me.paulhobbel.discordrp.proxy;

import com.google.common.base.Stopwatch;
import me.paulhobbel.discordrp.DiscordRP;
import me.paulhobbel.discordrp.api.DiscordRPPlugin;
import me.paulhobbel.discordrp.api.IDiscordRPPlugin;
import me.paulhobbel.discordrp.api.impl.Registry;
import me.paulhobbel.discordrp.utils.Log;
import me.paulhobbel.discordrp.utils.PluginUtils;
import net.minecraftforge.fml.common.event.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommonProxy implements IProxy {
    @Override
    public void onConstruction(FMLConstructionEvent event) {

    }

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {
        //Log.info("");
        DiscordRP.plugins = event.getAsmData().getAll(DiscordRPPlugin.class.getCanonicalName());
    }

    @Override
    public void onInit(FMLInitializationEvent event) {

    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event) {

    }

    @Override
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Map<Class<?>, IDiscordRPPlugin> plugins = new LinkedHashMap<>();

        Log.info("Registering plugins...");
        //plugins.remove(new DiscordRPPlugin()).register();
        PluginUtils.filterAnnotatedPlugins(plugins);

        for(Map.Entry<Class<?>, IDiscordRPPlugin> plugin : plugins.entrySet()) {
            Log.info("Registering plugin {}", plugin.getKey().getCanonicalName());
            plugin.getValue().register(Registry.getInstance());
        }

        Log.info("Registering plugins took {}", stopwatch.stop());
    }
}
