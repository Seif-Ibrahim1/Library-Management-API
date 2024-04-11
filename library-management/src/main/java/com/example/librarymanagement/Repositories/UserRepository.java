package com.example.librarymanagement.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.librarymanagement.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}