package com.steamsworld.sync.bungee.user;

import com.steamsworld.sync.bungee.BungeePlugin;
import com.steamsworld.sync.core.user.User;
import lombok.Getter;

import java.util.UUID;

/**
 * @author Steamworks (Steamworks#1127)
 * Sunday 06 2022 (7:08 PM)
 * proxy-sync (com.steamsworld.sync.bungee.user)
 */
public class BungeeUser extends User {
    public BungeeUser(UUID uuid, String serverName, String proxy) {
        super(
                uuid,
                serverName,
                proxy,
                BungeePlugin.getPlugin().getProxy().getPlayer(uuid).getName()
        );
    }
}
