package com.example.proyectotlc.repository;

import com.example.proyectotlc.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//@Query(value = "SELECT u.id as id, u.username as username, u.password as password FROM tb_usuario u WHERE u.username = ?1",nativeQuery = true)
    Optional<User> findByUsername(String username);
}