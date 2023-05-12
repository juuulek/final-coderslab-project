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

    List<String[]> mapTasksToCsvRows(List<Task> entities);

    default String[] mapTaskToArray(Task task) {
        TaskDto taskDto = mapToDto(task);
        return new String[]{taskDto.getId().toString(),
                taskDto.getListId().toString(),
                taskDto.getName() == null ? "" : taskDto.getName(),
                taskDto.getDescription() == null ? "" : taskDto.getDescription(),
                taskDto.getAppearance() == null ? "" : taskDto.getAppearance().toString(),
                taskDto.getAlert() == null ? "" : taskDto.getAlert().toString(),
                taskDto.getDeadline() == null ? "" : taskDto.getDeadline().toString(),
                taskDto.getDone() == null ? "" : taskDto.getDone().toString(),
                taskDto.getTags() == null ? "" : taskDto.getTags()};
    }
}
