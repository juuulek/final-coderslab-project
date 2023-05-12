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

    @Operation(summary = "Gets all tasks", description = "Gets list of tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response"),
            @ApiResponse(responseCode = "404", description = "Tasks cannot be found")
    })
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll() {
        List<TaskDto> lists = taskService.getAll();
        return lists.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(lists);
    }

    @Operation(summary = "Gets task", description = "Gets specific task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response"),
            @ApiResponse(responseCode = "404", description = "Task cannot be found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable Long id) {
        TaskDto dto = taskService.getById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @Operation(summary = "Posts task", description = "Adds task to data base")
    @PostMapping
    public ResponseEntity<TaskDto> add(@RequestBody @Valid TaskDto dto) {
        dto = taskService.add(dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Deletes task", description = "Deletes task base on id")
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDto> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Updates task", description = "Updates task base on id")
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable Long id, @RequestBody @Valid TaskDto dto) {
        dto = taskService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "\"Done\" task", description = "Marks task as done base on its id")
    @PutMapping("/done/{id}")
    public ResponseEntity<TaskDto> markAsDone(@PathVariable Long id) {
        TaskDto dto = taskService.markAsDone(id);
        return ResponseEntity.ok(dto);
    }
}
