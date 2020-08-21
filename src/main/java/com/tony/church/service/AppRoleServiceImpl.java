package com.tony.church.service;

import com.tony.church.dao.RoleRepository;
import com.tony.church.entity.AppRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;

@Service
public class AppRoleServiceImpl implements  AppRoleService {
    @Autowired
    RoleRepository roleRepository;


    @Override
    public List<String> allUserRoles(String userName) {
        return roleRepository.findAllAppAuthorityByAppUserName(userName);
    }

    @Override
    public void save(AppRole appRole) {
        roleRepository.save(appRole);
    }

    @Override
    public void remove(AppRole appRole) {
        roleRepository.delete(appRole);
    }

    @Override
    public void remove(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void removeByUsernameAndRole(String username, String role) {
        roleRepository.deleteUserRole(username, role);
    }
}
