package io.example.advancetodo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "list")
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 63)
    private String name;

    @NotNull
    @ManyToOne
    private User owner;

//    action
//    deleting — do zaimplementowania, jeżeli zdążę

    @ManyToMany
    private List<User> shared = new ArrayList<>();

    @OneToMany(mappedBy = "list")
    private List<Task> tasks;

    public TaskList(Long id) {
        this.id = id;
    }

    public String toHtml() {
        StringBuilder response = new StringBuilder("<div>" + name + "</div>");
        Flag odd = new Flag();
        if (tasks == null || tasks.isEmpty())
            response.append("brak zadań");
        else
            for (Task task : tasks)
                response.append("<div class=\"" + (odd.check() ? "odd" : "even") + "\">" + task.getName() + "</div>");
        return response.toString();
    }

    private static class Flag {
        boolean status = true;

        boolean check() {
            status = !status;
            return !status;
        }
    }
}
