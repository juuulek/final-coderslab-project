package io.example.advancetodo.services;

import io.example.advancetodo.dtos.UserDto;
import io.example.advancetodo.mappers.UserMapper;
import io.example.advancetodo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAll() {
        return userMapper.mapToDto(userRepository.findAll()); // nie podoba mi się tu "find"
    }


    // to do
}
