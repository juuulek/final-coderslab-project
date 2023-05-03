package io.example.advancetodo.controllers;

import io.example.advancetodo.services.TaskListService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lists")
@AllArgsConstructor
public class TaskListController {
    private final TaskListService taskListService;
}
