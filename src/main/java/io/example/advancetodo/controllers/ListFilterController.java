package io.example.advancetodo.controllers;

import io.example.advancetodo.dtos.ListFilterDto;
import io.example.advancetodo.services.ListFilterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Operation(summary = "Posts filter", description = "Adds filter to data base")
    @PostMapping
    public ResponseEntity<ListFilterDto> add(@RequestBody @Valid ListFilterDto dto) {
        dto = listFilterService.add(dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Deletes filter", description = "Deletes filter base on id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ListFilterDto> delete(@PathVariable Long id) {
        listFilterService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Updates filter", description = "Updates filter base on id")
    @PutMapping("/{id}")
    public ResponseEntity<ListFilterDto> update(@PathVariable Long id, @RequestBody @Valid ListFilterDto dto) {
        dto = listFilterService.update(id, dto);
        return ResponseEntity.ok(dto);
    }
}
