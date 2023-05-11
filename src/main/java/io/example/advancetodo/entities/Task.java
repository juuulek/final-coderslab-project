package io.example.advancetodo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
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

    private LocalDateTime appearance;

    private LocalDateTime alert;

    private LocalDateTime deadline;

    private LocalDateTime done;

    @Column(columnDefinition = "text")
    @Pattern(regexp = "([^\\W\\d_][^,]*)(,([^\\W\\d_][^,]*))*", message = "Tags are comma separated and each tag must start ISO basic Latin letter")
    // ([^\W\d_][^,]*)(,([^\W\d_][^,]*))*
    private String tags;

    public Task(Long id) {
        this.id = id;
    }

    public void setAppearance(LocalDateTime appearance) {
        if (appearance != null) {
            if (alert != null && alert.isBefore(appearance))
                throw new IllegalArgumentException("Appearance cannot be after alert");
            if (deadline != null && deadline.isBefore(appearance))
                throw new IllegalArgumentException("Appearance cannot be after deadline");
            if (done != null && done.isBefore(appearance))
                throw new IllegalArgumentException("Appearance cannot be after done");
        }
        this.appearance = appearance;
    }

    public void setAlert(LocalDateTime alert) {
        if (alert != null) {
            if (appearance != null && appearance.isAfter(alert))
                throw new IllegalArgumentException("Alert cannot be before appearance");
            if (deadline != null && deadline.isBefore(alert))
                throw new IllegalArgumentException("Alert cannot be after deadline");
            if (done != null && done.isBefore(alert))
                throw new IllegalArgumentException("Alert cannot be after done");
        }
        this.alert = alert;
    }

    public void setDeadline(LocalDateTime deadline) {
        if (deadline != null) {
            if (appearance != null && appearance.isAfter(deadline))
                throw new IllegalArgumentException("Deadline cannot be before appearance");
            if (alert != null && alert.isAfter(deadline))
                throw new IllegalArgumentException("Deadline cannot be before alert");
            if (done != null && done.isBefore(deadline))
                throw new IllegalArgumentException("Deadline cannot be after done");
        }
        this.deadline = deadline;
    }

    public void setDone(LocalDateTime done) {
        if (done != null) {
            if (appearance != null && appearance.isAfter(done))
                throw new IllegalArgumentException("Done cannot be before appearance");
            if (alert != null && alert.isAfter(done))
                throw new IllegalArgumentException("Done cannot be before alert");
            if (deadline != null && deadline.isAfter(done))
                throw new IllegalArgumentException("Done cannot be before deadline");
        }
        this.done = done;
    }

    public boolean hasTag(String compareTag) {
        for (String myTag : tags.split(","))
            if (myTag.equals(compareTag))
                return true;
        return false;
    }
}
