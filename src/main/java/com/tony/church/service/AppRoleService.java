package com.tony.church.service;

import com.tony.church.entity.AppRole;
import com.tony.church.entity.AppUser;

import java.util.List;

public interface AppRoleService {

    public void save(AppRole appRole);

    public void remove(AppRole appRole);

    public void remove(int id);
}
