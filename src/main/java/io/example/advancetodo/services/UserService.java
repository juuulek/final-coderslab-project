package io.example.advancetodo.services;

import io.example.advancetodo.dtos.UserDto;
import io.example.advancetodo.entities.User;
import io.example.advancetodo.mappers.UserMapper;
import io.example.advancetodo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAll() {
        return userMapper.mapToDto(userRepository.findAll()); // nie podoba mi się tu "find"
    }

    // pozostałe getty (by id, by login oraz by mail

    public UserDto add(UserDto dto) {
        User user = userMapper.mapToEntity(dto);
        Assert.isNull(user.getId(), "Id has to be null");
        userRepository.save(user);
        return userMapper.mapToDto(user);
    }


    // to do
}
