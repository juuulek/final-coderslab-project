package io.example.advancetodo.services;

import io.example.advancetodo.dtos.TaskDto;
import io.example.advancetodo.entities.Task;
import io.example.advancetodo.mappers.TaskMapper;
import io.example.advancetodo.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.InputMismatchException;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<TaskDto> getAll() {
        return taskMapper.mapToDto(taskRepository.findAll());
    }

    public TaskDto getById(Long id) {
        return taskMapper.mapToDto(taskRepository.findById(id).orElse(null));
    }

    public TaskDto add(TaskDto dto) {
        Task task = taskMapper.mapToEntity(dto);
        Assert.isNull(task.getId(), "Id has to be null");
        taskRepository.save(task);
        return taskMapper.mapToDto(task);
    }

    public void delete(Long id) {
        if (taskRepository.existsById(id))
            throw new InputMismatchException();
        taskRepository.deleteById(id);
    }

    public TaskDto update(Long id, TaskDto dto) {
        Assert.notNull(dto.getId(), "Id cannot be empty");
        if (!dto.getId().equals(id))
            throw new IllegalArgumentException();
        if (!taskRepository.existsById(id))
            throw new InputMismatchException();

        Task entity = taskMapper.mapToEntity(dto);
        taskRepository.save(entity);
        return taskMapper.mapToDto(entity);
    }
}