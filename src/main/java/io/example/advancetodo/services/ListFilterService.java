package io.example.advancetodo.services;

import io.example.advancetodo.dtos.ListFilterDto;
import io.example.advancetodo.entities.ListFilter;
import io.example.advancetodo.mappers.ListFilterMapper;
import io.example.advancetodo.repositories.ListFilterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
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

    public ListFilterDto add(ListFilterDto dto) {
        ListFilter listFilter = listFilterMapper.mapToEntity(dto);
        Assert.isNull(listFilter.getId(), "Id has to be null");
        listFilterRepository.save(listFilter);
        return listFilterMapper.mapToDto(listFilter);
    }

    @Transactional
    public void delete(Long id) {
        if (!listFilterRepository.existsById(id))
            throw new IllegalArgumentException("Filter doesn't exist");
        listFilterRepository.deleteById(id);
    }

    public ListFilterDto update(Long id, ListFilterDto dto) {
        Assert.notNull(dto.getId(), "Id cannot be empty");
        if (!dto.getId().equals(id))
            throw new IllegalArgumentException("Id's mismatch");
        if (!listFilterRepository.existsById(id))
            throw new IllegalArgumentException("Filter doesn't exist");

        ListFilter entity = listFilterMapper.mapToEntity(dto);
        listFilterRepository.save(entity);
        return listFilterMapper.mapToDto(entity);
    }
}
