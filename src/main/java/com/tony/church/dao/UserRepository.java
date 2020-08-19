package com.tony.church.dao;

import com.tony.church.entity.AppUser;
import com.tony.church.entity.ChurchEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<AppUser, Integer> {
    @Query( value="select A.authority as roles from authorities A " +
            "where A.username =:username ",
            nativeQuery = true )
    public List<String> findUserRoles(@Param("username") String username);
}
