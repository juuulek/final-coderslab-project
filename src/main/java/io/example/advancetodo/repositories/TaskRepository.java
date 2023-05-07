package io.example.advancetodo.repositories;

import io.example.advancetodo.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
