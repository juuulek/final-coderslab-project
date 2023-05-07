package io.example.advancetodo.mappers;

import io.example.advancetodo.dtos.TaskListDto;
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
        return user.getId();
    }

    default List<User> mapToUsers(List<Long> userIds) {
        List<User> users = new ArrayList<>();
        for (Long userId : userIds)
            users.add(new User(userId));
        return users;
    }
}
