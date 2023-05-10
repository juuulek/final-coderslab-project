package io.example.advancetodo.mappers;

import io.example.advancetodo.dtos.TaskListDto;
import io.example.advancetodo.entities.Task;
import io.example.advancetodo.entities.TaskList;
import io.example.advancetodo.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
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

    default List<User> mapToUsers(List<Long> userIds) {
        if (userIds == null)
            return null;
        List<User> users = new ArrayList<>();
        for (Long userId : userIds)
            users.add(new User(userId));
        return users;
    }

    default List<Task> mapToTask(List<Long> taskIds) {
        if (taskIds == null)
            return null;
        List<Task> tasks = new ArrayList<>();
        for (Long taskId : taskIds)
            tasks.add(new Task(taskId));
        return tasks;
    }

    default Long mapToTaskId(Task task) {
        if (task == null)
            return null;
        else
            return task.getId();
    }
}
