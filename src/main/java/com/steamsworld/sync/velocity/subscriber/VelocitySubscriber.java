package com.steamsworld.sync.velocity.subscriber;

import com.google.common.base.Joiner;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.steamsworld.sync.core.server.Server;
import com.steamsworld.sync.core.user.User;
import com.steamsworld.sync.velocity.VelocityPlugin;
import com.steamsworld.sync.velocity.server.VelocityServer;
import com.steamsworld.sync.velocity.user.VelocityUser;
import com.steamsworld.sync.velocity.user.VelocityUserHandler;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.event.connection.PostLoginEvent;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.velocitypowered.api.proxy.server.ServerInfo;
import com.velocitypowered.api.proxy.server.ServerPing;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Steamworks (Steamworks#1127)
 * Sunday 06 2022 (7:12 PM)
 * proxy-sync (com.steamsworld.sync.velocity.subscriber)
 */
@AllArgsConstructor
public class VelocitySubscriber {

    private final VelocityPlugin plugin;

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Subscribe
    public void onPostLoginEvent(PostLoginEvent event) {
        final Player player = event.getPlayer();

        plugin.getProxyServer().getScheduler()
            .buildTask(plugin, () -> {
                ServerConnection serverConnection = player.getCurrentServer().get();
                VelocityUser velocityUser = new VelocityUser(player.getUniqueId(), serverConnection.getServerInfo().getName(), plugin.getProxyName());
                plugin.getUserHandler().add(velocityUser);
            })
            .delay(500L, TimeUnit.MILLISECONDS)
            .schedule();
    }

    @Subscribe
    public void onDisconnectEvent(DisconnectEvent event) {
        final Player player = event.getPlayer();
        plugin.getUserHandler().remove(player.getUniqueId());
    }

    @Subscribe
    public void onServerConnectedEvent(ServerConnectedEvent event) {
        Player player = event.getPlayer();
        RegisteredServer registeredServer = event.getServer();
        ServerInfo serverInfo = registeredServer.getServerInfo();
        User user = plugin.getUserHandler().get(player.getUniqueId());

        user.setServerName(serverInfo.getName());
    }

    @Subscribe
    public void onProxyPingEvent(ProxyPingEvent event) {
        ServerPing serverPing = event.getPing();

        event.setPing(
                serverPing.asBuilder()
                        .onlinePlayers(plugin.getUserHandler().getAll().size())
                        .build()
        );
    }

    @SuppressWarnings("UnstableApiUsage")
    @Subscribe
    public void onPluginMessageEvent(PluginMessageEvent event) {
        if (!(event.getIdentifier().equals(VelocityPlugin.LEGACY_ID) || event.getIdentifier().equals(VelocityPlugin.MODERN_ID)))
            return;

        event.setResult(PluginMessageEvent.ForwardResult.handled());

        if (!(event.getSource() instanceof ServerConnection))
            return;

        ServerConnection serverConnection = (ServerConnection) event.getSource();

        ByteArrayDataInput dataInput = event.dataAsDataStream();
        ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();

        String sub = dataInput.readUTF();
        if (!Arrays.asList("PlayerCount", "PlayerList").contains(sub))
            return;

        String server = dataInput.readUTF();
        VelocityServer velocityServer = plugin.getServerHandler().get(server);
        if (sub.equals("PlayerCount")) {
            dataOutput.writeUTF("PlayerCount");
            if (!(server.equalsIgnoreCase("PROXY"))) {
                dataOutput.writeUTF("PROXY");
                dataOutput.writeInt(plugin.getUserHandler().size());
                serverConnection.getPlayer().sendPluginMessage(event.getIdentifier(), dataOutput.toByteArray());
                return;
            }

            dataOutput.writeUTF(server);
            dataOutput.writeInt(velocityServer.getOnlinePlayers().size());
            return;
        }

        dataOutput.writeUTF("PlayerCount");
        if (server.equalsIgnoreCase("PROXY")) {
            dataOutput.writeUTF("PROXY");

            List<String> usernames = new ArrayList<>();
            for(VelocityUser user : plugin.getUserHandler().getAll())
                usernames.add(user.getUsername());

            dataOutput.writeUTF(Joiner.on(',').join(usernames));
            serverConnection.getPlayer().sendPluginMessage(event.getIdentifier(), dataOutput.toByteArray());
            return;
        }

        dataOutput.writeUTF(server);

        List<String> usernames = new ArrayList<>();
        for(VelocityUser user : velocityServer.getOnlinePlayers())
            usernames.add(user.getUsername());

        dataOutput.writeUTF(Joiner.on(',').join(usernames));
        serverConnection.getPlayer().sendPluginMessage(event.getIdentifier(), dataOutput.toByteArray());
    }

}
