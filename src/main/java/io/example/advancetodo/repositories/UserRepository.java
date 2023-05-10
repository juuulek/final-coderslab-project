package io.example.advancetodo.repositories;

import io.example.advancetodo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    Optional<User> findByMail(String mail);

    boolean existsByLogin(String login);

    boolean existsByMail(String mail);
}
