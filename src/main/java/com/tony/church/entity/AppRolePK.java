package com.tony.church.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AppRolePK implements Serializable {

        private String username;
        private String authority;

        public AppRolePK() {
        }

        public String getUserName() {
            return username;
        }

        public void setUserName(String username) {
            this.username = username;
        }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public int hashCode() {
            return (int) username.hashCode() + 31;
        }

        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (!(obj instanceof AppRolePK)) return false;
            if (obj == null) return false;
            AppRolePK pk = (AppRolePK) obj;
            return pk.authority.equals(authority)  && pk.username.equals(username);
        }

}
