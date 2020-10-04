package com.mepros.springbootsecurity.repository;

import com.mepros.springbootsecurity.entities.Role;
import com.mepros.springbootsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT * FROM ROLES WHERE name = ?1", nativeQuery = true)
    Role findRoleByName(String name);
}
