package com.github.longkerdandy.evo.aerospike.entity;

/**
 * Device Entity
 */
@SuppressWarnings("unused")
public class Device {

    private String id;                      // Id
    private int type;                       // Type
    private String descId;                  // Description Id
    private String connected;               // Connected Node
    private int pv;                         // Protocol Version

    protected Device() {
    }

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescId() {
        return descId;
    }

    public void setDescId(String descId) {
        this.descId = descId;
    }

    public String getConnected() {
        return connected;
    }

    public void setConnected(String connected) {
        this.connected = connected;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }
}
