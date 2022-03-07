package com.steamsworld.sync.velocity.user;

import com.steamsworld.sync.core.user.UserHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Steamworks (Steamworks#1127)
 * Sunday 06 2022 (8:37 PM)
 * proxy-sync (com.steamsworld.sync.velocity.user)
 */
public class VelocityUserHandler implements UserHandler<VelocityUser> {

    private final List<VelocityUser> loadedUsers = new ArrayList<>();

    @Override
    public VelocityUser get(UUID uuid) {
        VelocityUser foundUser = null;
        for(VelocityUser loadedUser : loadedUsers) {
            if (loadedUser.getUuid().equals(uuid)) {
                foundUser = loadedUser;
                break;
            }
        }
        return Optional.ofNullable(foundUser).orElse(null);
    }

    @Override
    public boolean remove(UUID uuid) {
        VelocityUser foundUser = null;
        for(VelocityUser loadedUser : loadedUsers) {
            if (loadedUser.getUuid().equals(uuid)) {
                foundUser = loadedUser;
                break;
            }
        }

        return loadedUsers.remove(foundUser);
    }

    @Override
    public void add(VelocityUser velocityUser) {
        if(loadedUsers.contains(velocityUser))
            throw new IllegalStateException("This profile is already loaded into memory.");

        loadedUsers.add(velocityUser);
    }

    @Override
    public int size() {
        return loadedUsers.size();
    }

    @Override
    public List<VelocityUser> getAll() {
        return loadedUsers;
    }

}
