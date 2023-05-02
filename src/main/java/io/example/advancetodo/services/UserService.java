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
        return userMapper.mapToDto(userRepository.findAll());
    }

    public UserDto getById(Long id) {
        return userMapper.mapToDto(userRepository.findById(id).orElse(null));
    }

    public UserDto getByLogin(String login) {
        return userMapper.mapToDto(userRepository.findByLogin(login).orElse(null));
    }

    public UserDto getByMail(String mail) {
        return userMapper.mapToDto(userRepository.findByMail(mail).orElse(null));
    }

    public UserDto add(UserDto dto) {
        User user = userMapper.mapToEntity(dto);
        Assert.isNull(user.getId(), "Id has to be null");
        userRepository.save(user);
        return userMapper.mapToDto(user);
    }


    // to do
}
