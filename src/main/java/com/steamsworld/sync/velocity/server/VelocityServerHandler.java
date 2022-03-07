package com.steamsworld.sync.velocity.server;

import com.steamsworld.sync.core.server.ServerHandler;
import com.steamsworld.sync.velocity.user.VelocityUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Steamworks (Steamworks#1127)
 * Sunday 06 2022 (8:56 PM)
 * proxy-sync (com.steamsworld.sync.velocity.server)
 */
public class VelocityServerHandler implements ServerHandler<VelocityServer> {

    private final List<VelocityServer> servers = new ArrayList<>();

    @Override
    public VelocityServer get(String name) {
        VelocityServer foundServer = null;
        for(VelocityServer loadedServer : servers) {
            if (loadedServer.getName().equalsIgnoreCase(name)) {
                foundServer = loadedServer;
                break;
            }
        }
        return Optional.ofNullable(foundServer).orElse(null);
    }
}
