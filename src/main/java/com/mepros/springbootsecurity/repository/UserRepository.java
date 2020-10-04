package com.mepros.springbootsecurity.repository;

import com.mepros.springbootsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM USERS WHERE name = ?1", nativeQuery = true)
    User findUserByName(String name);
}
