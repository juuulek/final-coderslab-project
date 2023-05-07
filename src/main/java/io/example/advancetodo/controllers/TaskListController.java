package io.example.advancetodo.controllers;

import io.example.advancetodo.dtos.TaskListDto;
import io.example.advancetodo.services.TaskListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lists")
@AllArgsConstructor
public class TaskListController {
    private final TaskListService taskListService;

    @Operation(summary = "Gets all lists", description = "Gets list of task list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response"),
            @ApiResponse(responseCode = "404", description = "Lists cannot be found")
    })
    @GetMapping
    public ResponseEntity<List<TaskListDto>> getAll() {
        List<TaskListDto> lists = taskListService.getAll();
        return lists.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(lists);
    }

    @Operation(summary = "Gets list", description = "Gets specific task list by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response"),
            @ApiResponse(responseCode = "404", description = "List cannot be found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskListDto> getById(@PathVariable Long id) {
        TaskListDto dto = taskListService.getById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @Operation(summary = "Posts list", description = "Adds task list to data base")
    @PostMapping
    public ResponseEntity<TaskListDto> add(@RequestBody @Valid TaskListDto dto) {
        dto = taskListService.add(dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Deletes list", description = "Deletes task list base on id")
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskListDto> delete(@PathVariable Long id) {
        taskListService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Updates list", description = "Updates task list base on id")
    @PutMapping("/{id}")
    public ResponseEntity<TaskListDto> update(@PathVariable Long id, @RequestBody @Valid TaskListDto dto) {
        dto = taskListService.update(id, dto);
        return ResponseEntity.ok(dto);
    }
}
