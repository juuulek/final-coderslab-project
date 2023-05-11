package io.example.advancetodo.mappers;

import io.example.advancetodo.dtos.ListFilterDto;
import io.example.advancetodo.entities.ListFilter;
import io.example.advancetodo.entities.TaskList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListFilterMapper {
    @Mapping(source = "ownerId", target = "owner.id")
    ListFilter mapToEntity(ListFilterDto dto);

    @Mapping(source = "owner.id", target = "ownerId")
    ListFilterDto mapToDto(ListFilter entity);

    List<ListFilterDto> mapToDto(List<ListFilter> entities);

    default TaskList mapToTaskList(Long taskListId) {
        if (taskListId == null)
            return null;
        else
            return new TaskList(taskListId);
    }

    default Long mapToTaskListId(TaskList taskList) {
        if (taskList == null)
            return null;
        else
            return taskList.getId();
    }
}
