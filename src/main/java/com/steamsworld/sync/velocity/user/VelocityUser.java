package com.steamsworld.sync.velocity.user;

import com.steamsworld.sync.core.user.User;
import com.steamsworld.sync.velocity.VelocityPlugin;

import java.util.UUID;

/**
 * @author Steamworks (Steamworks#1127)
 * Sunday 06 2022 (6:59 PM)
 * proxy-sync (com.steamsworld.sync.velocity.user)
 */
public class VelocityUser extends User {

    public VelocityUser(UUID uuid, String serverName, String proxy) {
        super(
                uuid,
                serverName,
                proxy,
                VelocityPlugin.getPlugin().getProxyServer().getPlayer(uuid).getName()
        );
    }

}
