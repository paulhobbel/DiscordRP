package me.paulhobbel.discordrp.proxy;

import net.minecraftforge.fml.common.event.*;

public interface IProxy {
    void onConstruction(FMLConstructionEvent event);

    void onPreInit(FMLPreInitializationEvent event);

    void onInit(FMLInitializationEvent event);

    void onPostInit(FMLPostInitializationEvent event);

    void onLoadComplete(FMLLoadCompleteEvent event);
}
