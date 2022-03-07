package com.steamsworld.sync.bungee;

import com.steamsworld.sync.bungee.user.BungeeUser;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steamworks (Steamworks#1127)
 * Sunday 06 2022 (6:50 PM)
 * proxy-sync (io.github.steamworksmc.sync)
 */
@Getter
public class BungeePlugin extends Plugin {

    @Getter
    private static BungeePlugin plugin;

    private final List<BungeeUser> loadedUsers = new ArrayList<BungeeUser>();

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {

    }
}
