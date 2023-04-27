package io.example.advancetodo.controllers;

import io.example.advancetodo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    // to do
}
