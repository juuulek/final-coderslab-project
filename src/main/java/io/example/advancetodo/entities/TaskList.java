package io.example.advancetodo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "list")
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private User owner;

//    action — do zaimplementowania, jeżeli zdążę

    @ManyToMany
    private List<User> shared = new ArrayList<>();

    private LocalTime deleting; // do sprawdzenia, czy w ogóle mogę tę klasę użyć w tym celu
}
