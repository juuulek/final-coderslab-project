package io.example.advancetodo.controllers;

import io.example.advancetodo.dtos.UserDto;
import io.example.advancetodo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Gets all users", description = "Gets list of all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response",
                    content = @Content(schema = @Schema(implementation = UserDto[].class))),
            @ApiResponse(responseCode = "404", description = "Users cannot be found")
    })
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = userService.getAll();
        if (users.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(users);
    }

    @Operation(summary = "Gets user", description = "Gets specific user by id")
    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        UserDto dto = userService.getById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @Operation(summary = "Gets user", description = "Gets specific user by login")
    @GetMapping("/login/{login}")
    public ResponseEntity<UserDto> getByLogin(@PathVariable String login) {
        UserDto dto = userService.getByLogin(login);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @Operation(summary = "Gets user", description = "Gets specific user by e-mail")
    @GetMapping("/mail/{mail}")
    public ResponseEntity<UserDto> getByMail(@PathVariable String mail) {
        UserDto dto = userService.getByMail(mail);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @Operation(summary = "Posts user", description = "Adds user to data base")
    @PostMapping
    public ResponseEntity<UserDto> add(@RequestBody @Valid UserDto dto) {
        dto = userService.add(dto);
        return ResponseEntity.ok(dto);
    }


    // to do
}
