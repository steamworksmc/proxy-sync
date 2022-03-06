package io.github.steamworksmc.sync;

import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author Steamworks (Steamworks#1127)
 * Sunday 06 2022 (6:50 PM)
 * proxy-sync (io.github.steamworksmc.sync)
 */
public class ProxyPlugin extends Plugin {

    private static ProxyPlugin instance;
    public static ProxyPlugin get() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

    }
}
