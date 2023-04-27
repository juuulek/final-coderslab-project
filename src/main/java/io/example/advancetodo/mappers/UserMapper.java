package io.example.advancetodo.mappers;

import io.example.advancetodo.dtos.UserDto;
import io.example.advancetodo.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapToEntity(UserDto dto);

    UserDto mapToDto(User entity);

    List<UserDto> mapToDto(List<User> entities);
}
