package com.steamsworld.sync.velocity;

import com.google.inject.Inject;
import com.moandjiezana.toml.Toml;
import com.steamsworld.sync.velocity.server.VelocityServerHandler;
import com.steamsworld.sync.velocity.subscriber.VelocitySubscriber;
import com.steamsworld.sync.velocity.user.VelocityUserHandler;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.LegacyChannelIdentifier;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import lombok.Getter;
import org.slf4j.Logger;

import java.nio.file.Path;

/**
 * @author Steamworks (Steamworks#1127)
 * Sunday 06 2022 (6:58 PM)
 * proxy-sync (com.steamsworld.sync.velocity)
 */

@Getter
@Plugin(id = "proxy-sync", name = "Proxy Sync", version = "PRE-1.0.0", description = "Sync your Velocity proxies together.", url = "https://www.steamsworld.com/", authors = { "Steamworks" })
public class VelocityPlugin {

    @Getter
    private static VelocityPlugin plugin;

    public static final LegacyChannelIdentifier LEGACY_ID = new LegacyChannelIdentifier("BungeeCord");
    public static final MinecraftChannelIdentifier MODERN_ID = MinecraftChannelIdentifier.create("bungeecord", "main");

    private final ProxyServer proxyServer;
    private final Logger logger;
    private final Path path;

    private VelocityUserHandler userHandler;
    private VelocityServerHandler serverHandler;
    private String proxyName = "";

    @Inject
    public VelocityPlugin(ProxyServer proxyServer, Logger logger, Path path) {
        this.proxyServer = proxyServer;
        this.logger = logger;
        this.path = path;
    }

    @Subscribe
    public void onProxyInitializeEvent(ProxyInitializeEvent event) {
        plugin = this;

        proxyServer.getChannelRegistrar().register(
                LEGACY_ID,
                MODERN_ID
        );

        this.userHandler = new VelocityUserHandler();
        this.serverHandler = new VelocityServerHandler();

        proxyServer.getEventManager().register(
                this,
                new VelocitySubscriber(this)
        );
    }


}
