package io.example.advancetodo.services;

import io.example.advancetodo.dtos.ListFilterDto;
import io.example.advancetodo.mappers.ListFilterMapper;
import io.example.advancetodo.repositories.ListFilterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListFilterService {
    private final ListFilterRepository listFilterRepository;
    private final ListFilterMapper listFilterMapper;

    public List<ListFilterDto> getAll() {
        return listFilterMapper.mapToDto(listFilterRepository.findAll());
    }

    public ListFilterDto getById(Long id) {
        return listFilterMapper.mapToDto(listFilterRepository.findById(id).orElse(null));
    }

    // to do
}
