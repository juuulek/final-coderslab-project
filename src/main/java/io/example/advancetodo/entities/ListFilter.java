package io.example.advancetodo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "filters")
public class ListFilter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private User owner;

    @NotEmpty
    @ManyToMany
    private List<TaskList> lists = new ArrayList<>();

    @Column(columnDefinition = "text")
    @Pattern(regexp = "([^\\W\\d_][^,]*)(,([^\\W\\d_][^,]*))*", message = "Tags are comma separated and each tag must start ISO basic Latin letter")
    private String includedTags;

    @Column(columnDefinition = "text")
    @Pattern(regexp = "([^\\W\\d_][^,]*)(,([^\\W\\d_][^,]*))*", message = "Tags are comma separated and each tag must start ISO basic Latin letter")
    private String excludedTags;
    // W tym miejscu chciałbym zaznaczyć, że bardzo chciałbym uczynić filtry bardziej eleganckimi i zastosować jakieś inne
    // rozwiązanie, np. sugerowany przez Artura Querydsl bądź jakiś query language, ale nie wyrobiłem się ani z zapoznaniem
    // Querydsl, ani znalezieniem czegoś innego, ani z zaimplementowaniem czegoś szerszego (jak filtrowanie po deadline'ach),
    // więc niestety (ubolewanie autentyczne) musi pozostać mój pierwotny pomysł na zaliczenie

    public ListFilter(Long id) {
        this.id = id;
    }

    public List<Task> getTasks() {
        List<Task> result = new ArrayList<>();
        if (lists != null)
            for (TaskList taskList : lists)
                if (taskList.hasPermission(owner.getId()) && !taskList.getTasks().isEmpty())
                    for (Task task : taskList.getTasks()) {
                        boolean shouldAdd = false;
                        if (includedTags == null || includedTags.isEmpty())
                            shouldAdd = true;
                        else
                            for (String tag : includedTags.split(","))
                                if (task.hasTag(tag)) {
                                    shouldAdd = true;
                                    break;
                                }
                        if (shouldAdd)
                            for (String tag : excludedTags.split(","))
                                if (task.hasTag(tag)) {
                                    shouldAdd = false;
                                    break;
                                }
                        if (shouldAdd)
                            result.add(task);
                    }
        return result;
    }
}
