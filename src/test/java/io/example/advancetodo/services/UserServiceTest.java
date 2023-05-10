package io.example.advancetodo.services;

import io.example.advancetodo.dtos.UserDto;
import io.example.advancetodo.mappers.UserMapper;
import io.example.advancetodo.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;


class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnNoUsersIfThereWasNoSavedUsersBefore() {
        // given
        // nothing given

        // when
        UserDto nullUser = userService.getById(1L);
        List<UserDto> emptyList = userService.getAll();

        // then
        Assertions.assertNull(nullUser);
        Assertions.assertTrue(emptyList.isEmpty());
    }

//    @Test
//    public void should () {
//        // given
//        // when
//        // then
//    }
}
