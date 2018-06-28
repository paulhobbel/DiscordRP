package me.paulhobbel.discordrp.client;

import me.paulhobbel.discordrp.common.CommonProxy;

import me.paulhobbel.discordrp.common.Log;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = DiscordRP.MODID, name = DiscordRP.NAME, version = DiscordRP.VERSION, clientSideOnly = true)
public class DiscordRP {
    public static final String MODID = "discordrp";
    public static final String NAME = "Discord Rich Presence";
    public static final String VERSION = "${version}";

    @SidedProxy(modId = MODID, clientSide = "me.paulhobbel.discordrp.client.ClientProxy")
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void onConstruction(FMLConstructionEvent event) {
        // Yea this is my first mod so I am allowed to do this
        Log.info("Hello World!");
        proxy.onConstruction(event);
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        proxy.onPreInit(event);
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        proxy.onInit(event);
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        proxy.onPostInit(event);
    }

    @Mod.EventHandler
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        proxy.onLoadComplete(event);
    }
}
