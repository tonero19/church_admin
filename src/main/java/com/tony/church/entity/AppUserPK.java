package com.tony.church.entity;

public class AppUserPK {

    private String username;

    public AppUserPK() {
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public int hashCode() {
        return (int) username.hashCode() + 31;
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof AppUserPK)) return false;
        if (obj == null) return false;
        AppUserPK pk = (AppUserPK) obj;
        return pk.username.equals(username);
    }

}
