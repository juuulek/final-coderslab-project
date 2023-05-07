package io.example.advancetodo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Schema(description = "Id of owner of this list", example = "12")
    @NotNull
    private Long ownerId;

//    action
//    deleting — do zaimplementowania, jeżeli zdążę

    @Schema(description = "IDs of the people this list is shared with")
    private List<Long> shared = new ArrayList<>();
}
