package io.example.advancetodo.mappers;

import io.example.advancetodo.dtos.TaskDto;
import io.example.advancetodo.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(source = "listId", target = "list.id")
    Task mapToEntity(TaskDto dto);

    @Mapping(source = "list.id", target = "listId")
    TaskDto mapToDto(Task entity);

    List<TaskDto> mapToDto(List<Task> entities);
}
