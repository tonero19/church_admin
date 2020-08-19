package com.tony.church.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class AppUser extends  AbstractEntity {

    @NotNull(message="username field cannot be empty")
    @Column(name= "username")
    private String appUserName;

    @NotNull(message="password field cannot be empty")
    @Column(name= "password")
    private String appPassword;

    @Column(name= "enabled")
    private Boolean enabled;

    @Transient
    private List<String> roles = new ArrayList<>();

    @Transient
    private List<String> selectedRoles = new ArrayList<>();

    public AppUser() {
        roles.add("ROLE_MEMBER"); roles.add("ROLE_MANAGER");
        roles.add("ROLE_ADMIN");
    }

    public List<String> getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(List<String> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    public String getAppPassword() {
        return appPassword;
    }

    public void setAppPassword(String appPassword) {
        this.appPassword = appPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getAppUserName() {
        return appUserName;
    }

    public void setAppUserName(String appUserName) {
        this.appUserName = appUserName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUser)) return false;
        return getAppUserName() != null && getAppUserName().equalsIgnoreCase(((AppUser) o).getAppUserName());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "appUserName='" + appUserName + '\'' +
                ", appPassword='" + appPassword + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
