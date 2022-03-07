package com.steamsworld.sync.bungee.server;

import com.steamsworld.sync.bungee.BungeePlugin;
import com.steamsworld.sync.bungee.user.BungeeUser;
import com.steamsworld.sync.core.server.Server;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steamworks (Steamworks#1127)
 * Sunday 06 2022 (7:07 PM)
 * proxy-sync (com.steamsworld.sync.bungee.server)
 */
public class BungeeServer extends Server<BungeeUser> {
    public BungeeServer(String name) {
        super(name);
    }

    public List<BungeeUser> getOnlinePlayers() {
        List<BungeeUser> onlinePlayers = new ArrayList<BungeeUser>();
        for(BungeeUser loadedUser : BungeePlugin.getPlugin().getLoadedUsers())
            if(loadedUser.getServerName().equals(name))
                onlinePlayers.add(loadedUser);
        return onlinePlayers;
    }
}
