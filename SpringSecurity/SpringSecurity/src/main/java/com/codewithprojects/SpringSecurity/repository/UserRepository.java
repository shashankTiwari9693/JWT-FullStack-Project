package com.codewithprojects.SpringSecurity.repository;

import com.codewithprojects.SpringSecurity.entities.Role;
import com.codewithprojects.SpringSecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
