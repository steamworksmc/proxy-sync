package com.steamsworld.sync.velocity.server;

import com.steamsworld.sync.core.server.Server;
import com.steamsworld.sync.velocity.VelocityPlugin;
import com.steamsworld.sync.velocity.user.VelocityUser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steamworks (Steamworks#1127)
 * Sunday 06 2022 (6:58 PM)
 * proxy-sync (com.steamsworld.sync.velocity.server)
 */
public class VelocityServer extends Server<VelocityUser> {

    public VelocityServer(String name) {
        super(name);
    }

    public List<VelocityUser> getOnlinePlayers() {
        List<VelocityUser> onlinePlayers = new ArrayList<VelocityUser>();
        for(VelocityUser loadedUser : VelocityPlugin.getPlugin().getLoadedUsers())
            if(loadedUser.getServerName().equals(name))
                onlinePlayers.add(loadedUser);
        return onlinePlayers;
    }

}
