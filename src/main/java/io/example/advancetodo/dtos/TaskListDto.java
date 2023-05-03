package io.example.advancetodo.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.example.advancetodo.entities.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TaskListDto {
    @Schema(description = "List id", example = "12")
    private Long id;

    @Schema(description = "Name (very short description) of list", example = "house chores")
    @Size(min = 3, max = 63)
    private String name;

    @Schema(description = "Id od owner of this list", example = "12")
    @NotNull
    @ManyToOne
    private User owner;

//    action — do zaimplementowania, jeżeli zdążę

    @ManyToMany
    private List<User> shared = new ArrayList<>();

    @Schema(description = "The time after which the record will be deleted", example = "02-00-0000 00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime deleting;
}
