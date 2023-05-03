package io.example.advancetodo.repositories;

import io.example.advancetodo.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
}
