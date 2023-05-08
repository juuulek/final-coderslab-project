package io.example.advancetodo.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDto {
    @Schema(description = "Task id", example = "11")
    private Long id;

    @Schema(description = "List id", example = "12")
    @NotNull
    private Long listId;

    @Schema(description = "Name (short description) of this task", example = "change light bulb")
    @Column(columnDefinition = "varchar(63)", nullable = false)
    private String name;

    @Schema(description = "Optional description of this task. It could be long", example = "use LED, E27\n" +
            "in the wc, behind the curtain, in the cupboard behind the toilet bowl, in the first aid kit, in the cocoa box labeled \"salt\"")
    @Column(columnDefinition = "text")
    private String description;

    @Schema(description = "Task appearance time. Before this moment front app should hide / greyed out / inactive / etc. this task",
            example = "09-09-2009 09:09")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime appearance;


    @Schema(description = "Task alert time. After this moment front app should highlight this task", example = "10-10-2010 10:10")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime alert;

    @Schema(description = "Task deadline", example = "11-11-2011 11:11")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime deadline;

    @Schema(description = "The time when the task was marked as done. After this moment front app should f.ex. crossed out this task",
            example = "12-12-2012 12:12")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime done;

    @Schema(description = "Tags as plaintext. Should be separated by comma", example = "bedroom, only in daylight")
    @Column(columnDefinition = "text")
    @Pattern(regexp = "([^\\W\\d_][^,]*)(,([^\\W\\d_][^,]*))*", message = "Tags are comma separated and each tag must start ISO basic Latin letter")
    private String tags;
}
