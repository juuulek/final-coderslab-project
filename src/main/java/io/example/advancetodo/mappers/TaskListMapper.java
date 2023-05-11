package io.example.advancetodo.mappers;

import io.example.advancetodo.dtos.TaskListDto;
import io.example.advancetodo.entities.Task;
import io.example.advancetodo.entities.TaskList;
import io.example.advancetodo.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskListMapper {
    @Mapping(source = "ownerId", target = "owner.id")
    TaskList mapToEntity(TaskListDto dto);

    @Mapping(source = "owner.id", target = "ownerId")
    TaskListDto mapToDto(TaskList entity);

    List<TaskListDto> mapToDto(List<TaskList> entities);

    default Long mapToUserId(User user) {
        if (user == null)
            return null;
        else
            return user.getId();
    }

    default User mapToUser(Long userId) {
        if (userId == null)
            return null;
        else
            return new User(userId);
    }

    default Task mapToTask(Long taskId) {
        if (taskId == null)
            return null;
        else
            return new Task(taskId);
    }

    default Long mapToTaskId(Task task) {
        if (task == null)
            return null;
        else
            return task.getId();
    }
}
