package com.github.longkerdandy.evo.api.entity;

/**
 * User Entity
 */
@SuppressWarnings("unused")
public class User {

    private String id;          // id
    private String alias;       // name, nick name
    private String email;       // email
    private String mobile;      // mobile phone
    private String password;    // password (encoded before saving to db)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
