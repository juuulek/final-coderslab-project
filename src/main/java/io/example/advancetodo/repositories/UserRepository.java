package io.example.advancetodo.repositories;

import io.example.advancetodo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    Optional<User> findByMail(String mail);

    // jeżeli starczy czasu, chcę tu dorzucić coś zliczającego wszystkich użytkowników, aby wrzucić to w homecontroller
}
