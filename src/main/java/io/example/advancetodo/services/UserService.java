package io.example.advancetodo.services;

import io.example.advancetodo.dtos.UserDto;
import io.example.advancetodo.entities.User;
import io.example.advancetodo.mappers.UserMapper;
import io.example.advancetodo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TaskListService taskListService;

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

    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id))
            throw new IllegalArgumentException("User cannot be found");
        userRepository.deleteById(id);
    }

    public UserDto update(Long id, UserDto dto) {
        Assert.notNull(dto.getId(), "Id cannot be empty");
        if (!dto.getId().equals(id))
            throw new IllegalArgumentException("Id's mismatch");
        if (!userRepository.existsById(id))
            throw new IllegalArgumentException("User cannot be found");

        User entity = userMapper.mapToEntity(dto);
        userRepository.save(entity);
        return userMapper.mapToDto(entity);
    }
}
