package com.tony.church.dao;

import com.tony.church.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleRepository extends JpaRepository<AppRole, Integer> {

    public List<String> findAllAppAuthorityByAppUserName(String appUserName);

    public long deleteByAppUserNameAndAppAuthority(@Param("appUserName") String appUserName, @Param("appAuthority") String appAuthority);

    @Transactional // because it's a native query
    @Modifying // because it's a native query
    @Query(value="delete from authorities " +
            " where username =:username and authority =:authority ",
            nativeQuery = true)
    public void deleteUserRole(@Param("username") String username, @Param("authority") String authority);

}
