package com.tony.church.service;

import com.tony.church.entity.AppUser;
import com.tony.church.entity.ChurchEvent;

import java.util.List;

public interface AppUserService {
    public List<AppUser> findAll();

    public AppUser findById(int id);

    public List<String> findUserRoles(String username);

    public void save(AppUser appUser);

    public void remove(AppUser appUser);

    public void remove(int id);
}
