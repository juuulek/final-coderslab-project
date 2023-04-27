package io.example.advancetodo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "filters")
public class ListFilter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToMany
    private List<TaskList> lists = new ArrayList<>();

    @NotNull
    @ManyToOne
    private User owner;

    // jeżeli zdążę, dodać filtry czasu po:
    // → appearance (momentem pojawienia się na liście)
    // → alert (momentem przypomnienia)
    // → deadline (momentem ostatecznego wykonania)
    // → done (momentem faktycznego wykonania)
    // z wariantami:
    // → bezwzględnym (od DateTime do DateTime)
    // → względnym (czyli od przedwczoraj, od teraz+2h itp.)
    // → a także uwzględnieniem, czy powyższe nie mają nulla
    // niestety czas na samo przemyślenie jest niemarginalny

    @Column(columnDefinition = "text")
    private String includedTags;

    @Column(columnDefinition = "text")
    private String excludedTags;
}
