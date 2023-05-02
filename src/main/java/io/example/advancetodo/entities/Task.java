package io.example.advancetodo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private TaskList list;

    @Column(columnDefinition = "varchar(63)", nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    private LocalDateTime appearance;

    private LocalDateTime alert;

    private LocalDateTime deadline;

    private LocalDateTime done;

    @Column(columnDefinition = "text")
    private String tags;
}
