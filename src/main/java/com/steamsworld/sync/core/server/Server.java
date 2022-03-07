package com.steamsworld.sync.core.server;

import com.steamsworld.sync.core.user.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steamworks (Steamworks#1127)
 * Sunday 06 2022 (6:54 PM)
 * proxy-sync (com.steamsworld.sync.core.server)
 */
@Data
public abstract class Server<T> {

    protected final String name;

    public abstract List<T> getOnlinePlayers();
}
