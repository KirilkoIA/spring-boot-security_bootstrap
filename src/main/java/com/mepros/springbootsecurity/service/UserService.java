package com.mepros.springbootsecurity.service;

import com.mepros.springbootsecurity.entities.Role;
import com.mepros.springbootsecurity.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    void addUser(User user);

    void updateUser(User user);

    void removeUserById(long id);

    User getUserById(long id);

    List<User> getAllUsers();

    User getUserByName(String name);

    Role getRoleByName(String name);

    Set<Role> getAllRoles();
}
