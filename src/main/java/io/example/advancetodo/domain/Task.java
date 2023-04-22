package io.example.advancetodo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    private LocalDateTime apperance;

    private LocalDateTime alert;

    private LocalDateTime deadline;

    private LocalDateTime done;

    @Column(columnDefinition = "text")
    private String tags;
}
