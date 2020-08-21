package com.tony.church.service;

import com.tony.church.dao.UserRepository;
import com.tony.church.entity.AppUser;
import com.tony.church.entity.ChurchEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements  AppUserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AppUser findById(int id) {

        Optional<AppUser> result = userRepository.findById(id);
        if(result.isPresent())
            return result.get();

        return null;
    }

    @Override
    public List<String> findUserRoles(String username) {
        return userRepository.findUserRoles(username);
    }

    @Override
    public boolean usernameTaken(String username) {
        return userRepository.existsAppUserByAppUserName(username);
    }

    @Override
    public void save(AppUser appUser) {
        userRepository.save(appUser);
    }

    @Override
    public void remove(AppUser appUser) {
        userRepository.delete(appUser);
    }

    @Override
    public void remove(int id) {
        userRepository.deleteById(id);
    }
}
