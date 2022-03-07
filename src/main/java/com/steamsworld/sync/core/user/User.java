package com.steamsworld.sync.core.user;

import lombok.*;

import java.util.UUID;

/**
 * @author Steamworks (Steamworks#1127)
 * Sunday 06 2022 (6:55 PM)
 * proxy-sync (com.steamsworld.sync.core.user)
 */
@RequiredArgsConstructor
@Getter
@Setter
public abstract class User {

    private final UUID uuid;
    private final String proxy;
    private String username, serverName;


}
