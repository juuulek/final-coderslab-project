package io.example.advancetodo.repositories;

import io.example.advancetodo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // jeżeli starczy czasu, chcę tu dorzucić coś zliczającego wszystkich użytkowników, aby wrzucić to w homecontroller
}
