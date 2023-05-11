package io.example.advancetodo.repositories;

import io.example.advancetodo.entities.ListFilter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListFilterRepository extends JpaRepository<ListFilter, Long> {
}
