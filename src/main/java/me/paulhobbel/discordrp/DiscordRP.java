package me.paulhobbel.discordrp;

import me.paulhobbel.discordrp.common.CommonProxy;

import me.paulhobbel.discordrp.common.Log;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.event.*;

import java.util.Set;

@Mod(modid = DiscordRP.MODID, name = DiscordRP.NAME, version = DiscordRP.VERSION, clientSideOnly = true, certificateFingerprint = DiscordRP.FINGERPRINT, updateJSON = DiscordRP.UPDATEJSON)
public class DiscordRP {
    public static final String MODID = "discordrp";
    public static final String NAME = "Discord Rich Presence";
    public static final String VERSION = "@VERSION@";
    public static final String FINGERPRINT = "5b6b19557df723bd85d474a32d64e4e191a2b42a";
    public static final String UPDATEJSON = "https://raw.githubusercontent.com/ItsDizzy/DiscordRP/master/update.json";

    public static Set<ASMDataTable.ASMData> plugins;

    @SidedProxy(modId = MODID, clientSide = "me.paulhobbel.discordrp.client.ClientProxy")
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
        Log.warn("This version was not signed properly, this is probably a development build and will not receive any support, use at own risk!");
    }

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
