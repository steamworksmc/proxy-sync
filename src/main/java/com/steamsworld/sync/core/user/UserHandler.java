package com.steamsworld.sync.core.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Steamworks (Steamworks#1127)
 * Sunday 06 2022 (8:36 PM)
 * proxy-sync (com.steamsworld.sync.core.user)
 */
public interface UserHandler<T> {

    T get(UUID uuid);
    boolean remove(UUID uuid);
    void add(T user);
    int size();
    List<T> getAll();

}
