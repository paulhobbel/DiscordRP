package me.paulhobbel.discordrp.common;

import net.minecraftforge.fml.common.event.*;

public interface CommonProxy {
    void onConstruction(FMLConstructionEvent event);

    void onPreInit(FMLPreInitializationEvent event);

    void onInit(FMLInitializationEvent event);

    void onPostInit(FMLPostInitializationEvent event);

    void onLoadComplete(FMLLoadCompleteEvent event);
}
