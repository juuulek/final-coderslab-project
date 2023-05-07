package io.example.advancetodo.mappers;

import io.example.advancetodo.dtos.TaskDto;
import io.example.advancetodo.entities.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task mapToEntity(TaskDto dto);

    TaskDto mapToDto(Task entity);

    List<TaskDto> mapToDto(List<Task> entity);
}
