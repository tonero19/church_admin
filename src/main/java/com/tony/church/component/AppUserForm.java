package com.tony.church.component;

import java.util.Arrays;
import java.util.List;

public class AppUserForm {
    private int id;
    private List<String> selectedRoles;
    private List<String> roles;
    private String idsAsSingleString = "";
    private String userName;
    private  String password;
    private Integer roleId;

    public List<String> getSelectedRoles() {
        return selectedRoles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSelectedRoles(List<String> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    public Object[] getRoles() {
        return roles.toArray();
    }

    public void setRoles(String[] roles) {
        this.roles = Arrays.asList(roles);
    }

    public String getIdsAsSingleString() {
        return idsAsSingleString;
    }

    public void setIdsAsSingleString(String idsAsSingleString) {
        this.idsAsSingleString = idsAsSingleString;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
