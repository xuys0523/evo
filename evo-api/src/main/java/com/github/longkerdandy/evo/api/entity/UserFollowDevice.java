package com.github.longkerdandy.evo.api.entity;

import com.arangodb.annotations.DocumentKey;

/**
 * User Follow Device Relation
 */
@SuppressWarnings("unused")
public class UserFollowDevice {

    @DocumentKey
    private String id;      // id
    private int permission; // permission

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
