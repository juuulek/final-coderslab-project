package io.example.advancetodo.controllers;

import io.example.advancetodo.dtos.TaskDto;
import io.example.advancetodo.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor

public class TaskController {
    private final TaskService taskService;

    @Operation(summary = "Gets all lists", description = "Gets list of task list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response"),
            @ApiResponse(responseCode = "404", description = "Lists cannot be found")
    })
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll() {
        List<TaskDto> lists = taskService.getAll();
        return lists.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(lists);
    }

    @Operation(summary = "Gets list", description = "Gets specific task list by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response"),
            @ApiResponse(responseCode = "404", description = "List cannot be found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable Long id) {
        TaskDto dto = taskService.getById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @Operation(summary = "Posts list", description = "Adds task list to data base")
    @PostMapping
    public ResponseEntity<TaskDto> add(@RequestBody @Valid TaskDto dto) {
        dto = taskService.add(dto);
        return ResponseEntity.ok(dto);
    }

    // to do
}
