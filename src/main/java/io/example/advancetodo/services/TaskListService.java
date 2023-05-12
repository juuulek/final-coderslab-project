package io.example.advancetodo.services;

import com.opencsv.CSVWriter;
import io.example.advancetodo.dtos.TaskListDto;
import io.example.advancetodo.entities.Task;
import io.example.advancetodo.entities.TaskList;
import io.example.advancetodo.mappers.TaskListMapper;
import io.example.advancetodo.mappers.TaskMapper;
import io.example.advancetodo.repositories.TaskListRepository;
import io.example.advancetodo.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskListService {
    private final TaskListRepository taskListRepository;
    private final TaskListMapper taskListMapper;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<TaskListDto> getAll() {
        return taskListMapper.mapToDto(taskListRepository.findAll());
    }

    public TaskListDto getById(Long id) {
        return taskListMapper.mapToDto(taskListRepository.findById(id).orElse(null));
    }

    public TaskListDto add(TaskListDto dto) {
        TaskList taskList = taskListMapper.mapToEntity(dto);
        Assert.isNull(taskList.getId(), "Id has to be null");
        for (Long sharedId : dto.getShared())
            if (dto.getOwnerId().equals(sharedId))
                throw new IllegalArgumentException("Task list cannot be shared with its owner");

        taskListRepository.save(taskList);
        return taskListMapper.mapToDto(taskList);
    }

    @Transactional
    public void delete(Long id) {
        if (!taskListRepository.existsById(id))
            throw new IllegalArgumentException("Task list doesn't exist");
        taskRepository.deleteAllByList_Id(id);
        taskListRepository.deleteById(id);
    }

    public TaskListDto update(Long id, TaskListDto dto) {
        Assert.notNull(dto.getId(), "Id cannot be empty");
        if (!dto.getId().equals(id))
            throw new IllegalArgumentException("Id's mismatch");
        if (!taskListRepository.existsById(id))
            throw new IllegalArgumentException("Task list doesn't exist");
        for (Long sharedId : dto.getShared())
            if (dto.getOwnerId().equals(sharedId))
                throw new IllegalArgumentException("Task list cannot be shared with its owner");

        TaskList entity = taskListMapper.mapToEntity(dto);
        taskListRepository.save(entity);
        return taskListMapper.mapToDto(entity);
    }

    public TaskListDto saveAsCSV(Long id) throws IOException {
        TaskList taskList = taskListRepository.findById(id).orElse(null);
        if (taskList == null)
            throw new IllegalArgumentException("Task list doesn't exist");
        List<Task> tasks = taskList.getTasks();
        if (tasks == null || tasks.isEmpty())
            throw new IllegalArgumentException("Task list has no tasks");
        List<String[]> csvRows = taskMapper.mapTasksToCsvRows(tasks);

        try (CSVWriter writer = new CSVWriter(new FileWriter(taskList.getName() + ".csv"))) {
            writer.writeAll(csvRows);
        }

        return taskListMapper.mapToDto(taskList);
    }
}
