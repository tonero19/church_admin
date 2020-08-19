package com.tony.church.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="authorities")
public class AppRole extends  AbstractEntity {

    @NotNull(message="username column cannot be empty")
    @Column(name= "username")
    private String appUserName;

    @NotNull(message="role column cannot be empty")
    @Column(name= "authority")
    private String appAuthority;

    public String getAppUserName() {
        return appUserName;
    }

    public void setAppUserName(String appUserName) {
        this.appUserName = appUserName;
    }

    public String getAppAuthority() {
        return appAuthority;
    }

    public void setAppAuthority(String appAuthority) {
        this.appAuthority = appAuthority;
    }
}
