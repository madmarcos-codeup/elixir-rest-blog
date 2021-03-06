package com.example.restblog.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);
    User findByEmail(String email);

}
