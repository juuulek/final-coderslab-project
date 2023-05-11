package io.example.advancetodo.mappers;

import io.example.advancetodo.dtos.UserDto;
import io.example.advancetodo.entities.ListFilter;
import io.example.advancetodo.entities.TaskList;
import io.example.advancetodo.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapToEntity(UserDto dto);

    UserDto mapToDto(User entity);

    List<UserDto> mapToDto(List<User> entities);

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

    default ListFilter mapToListFilter(Long id) {
        if (id == null)
            return null;
        else
            return new ListFilter(id);
    }

    default Long mapToListFilterId(ListFilter listFilter) {
        if (listFilter == null)
            return null;
        else
            return listFilter.getId();
    }
}
