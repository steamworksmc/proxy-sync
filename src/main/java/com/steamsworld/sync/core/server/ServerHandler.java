package com.steamsworld.sync.core.server;

import java.util.UUID;

/**
 * @author Steamworks (Steamworks#1127)
 * Sunday 06 2022 (8:55 PM)
 * proxy-sync (com.steamsworld.sync.core.server)
 */
public interface ServerHandler<T> {

    T get(String name);

}
