package io.example.advancetodo.services;

import io.example.advancetodo.dtos.TaskListDto;
import io.example.advancetodo.entities.TaskList;
import io.example.advancetodo.mappers.TaskListMapper;
import io.example.advancetodo.repositories.TaskListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.InputMismatchException;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskListService {
    private final TaskListRepository taskListRepository;
    private final TaskListMapper taskListMapper;

    public List<TaskListDto> getAll() {
        return taskListMapper.mapToDto(taskListRepository.findAll());
    }

    public TaskListDto getById(Long id) {
        return taskListMapper.mapToDto(taskListRepository.findById(id).orElse(null));
    }

    public TaskListDto add(TaskListDto dto) {
        TaskList taskList = taskListMapper.mapToEntity(dto);
        Assert.isNull(taskList.getId(), "Id has to be null");
        taskListRepository.save(taskList);
        return taskListMapper.mapToDto(taskList);
    }

    public void delete(Long id) {
        if (taskListRepository.existsById(id))
            throw new InputMismatchException();
        taskListRepository.deleteById(id);
    }

    public TaskListDto update(Long id, TaskListDto dto) {
        Assert.notNull(dto.getId(), "Id cannot be empty");
        if (!dto.getId().equals(id))
            throw new IllegalArgumentException();
        if (!taskListRepository.existsById(id))
            throw new InputMismatchException();

        TaskList entity = taskListMapper.mapToEntity(dto);
        taskListRepository.save(entity);
        return taskListMapper.mapToDto(entity);
    }
}
