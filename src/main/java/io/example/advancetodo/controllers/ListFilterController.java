package io.example.advancetodo.controllers;

import io.example.advancetodo.dtos.ListFilterDto;
import io.example.advancetodo.services.ListFilterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/filters")
@AllArgsConstructor
public class ListFilterController {
    private final ListFilterService listFilterService;

    @Operation(summary = "Gets all filters", description = "Gets list of filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response"),
            @ApiResponse(responseCode = "404", description = "Filter cannot be found")
    })
    @GetMapping
    public ResponseEntity<List<ListFilterDto>> getAll() {
        List<ListFilterDto> filters = listFilterService.getAll();
        return filters.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(filters);
    }

    @Operation(summary = "Gets filter", description = "Gets specific filter by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response"),
            @ApiResponse(responseCode = "404", description = "Filter cannot be found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ListFilterDto> getById(@PathVariable Long id) {
        ListFilterDto dto = listFilterService.getById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    // to do
}
