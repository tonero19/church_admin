package com.tony.church.service;

import com.tony.church.entity.AppRole;
import com.tony.church.entity.AppUser;

import java.util.List;

public interface AppRoleService {

    public List<String> allUserRoles(String userName);

    public void save(AppRole appRole);

    public void remove(AppRole appRole);

    public void remove(int id);

    public void removeByUsernameAndRole(String username, String role);
}
