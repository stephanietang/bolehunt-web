package com.bolehunt.mapper;

import com.bolehunt.domain.Role;
import com.bolehunt.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    void insertUser(User user);

    void insertUserRole(@Param("userId")Integer userId, @Param("roleId")Integer roleId);

    Role findRoleByRoleName(String roleName);
    
    User findByUserId(Integer userId);

    User findByUsername(String username);
    
    List<User> findAllUsers();
    
}