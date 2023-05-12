package io.example.advancetodo.services;

import io.example.advancetodo.dtos.UserDto;
import io.example.advancetodo.entities.User;
import io.example.advancetodo.mappers.UserMapper;
import io.example.advancetodo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetUserById() {
        // given
        final Long ID = 1L;
        final String LOGIN = "testowy";
        User user = new User();
        user.setLogin(LOGIN);
        user.setId(ID);
        UserDto userDto = new UserDto();
        userDto.setLogin(LOGIN);
        userDto.setId(ID);

        // when
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        when(userMapper.mapToDto(user)).thenReturn(userDto);
        UserDto result = userService.getById(ID);

        // then
        verify(userRepository, times(1)).findById(ID);
        verify(userMapper, times(1)).mapToDto(user);
        assertNotNull(result);
        assertEquals(userDto, result);
    }

//    @Test
//    public void should () {
//        // given
//        // when
//        // then
//    }
}
