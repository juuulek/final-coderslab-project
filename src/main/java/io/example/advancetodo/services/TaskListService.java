package io.example.advancetodo.services;

import io.example.advancetodo.mappers.UserMapper;
import io.example.advancetodo.repositories.TaskListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskListService {
    private final TaskListRepository taskListRepository;
    private final UserMapper userMapper;
}
