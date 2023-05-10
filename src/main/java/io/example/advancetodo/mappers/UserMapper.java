package io.example.advancetodo.mappers;

import io.example.advancetodo.dtos.UserDto;
import io.example.advancetodo.entities.TaskList;
import io.example.advancetodo.entities.User;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapToEntity(UserDto dto);

    UserDto mapToDto(User entity);

    List<UserDto> mapToDto(List<User> entities);

    default List<TaskList> mapToTaskList(List<Long> taskListIds) {
        List<TaskList> taskLists = new ArrayList<>();
        if (taskListIds != null)
            for (Long taskListId : taskListIds)
                taskLists.add(new TaskList(taskListId));
        return taskLists;
    }

    default Long mapToTaskListId(TaskList taskList) {
        if (taskList == null)
            return null;
        else
            return taskList.getId();
    }
}
