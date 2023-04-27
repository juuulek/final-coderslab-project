package io.example.advancetodo.services;

import io.example.advancetodo.mappers.UserMapper;
import io.example.advancetodo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // to do
}
