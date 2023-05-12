package io.example.advancetodo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ListFilterDto {
    @Schema(description = "Task id", example = "10")
    private Long id;

    @Schema(description = "Name (very short description) of filter", example = "house")
    @Size(min = 3, max = 63)
    private String name;

    @Schema(description = "Id of owner of this filter", example = "13")
    @NotNull
    private Long ownerId;

    @Schema(description = "Only tasks on these lists are to be included")
    @NotEmpty
    private List<Long> lists = new ArrayList<>();

    @Schema(description = "If null all tasks included. Otherwise, tasks without these tags are to be excluded", example = "only in daylight")
    @Column(columnDefinition = "text")
    @Pattern(regexp = "([^\\W\\d_][^,]*)(,([^\\W\\d_][^,]*))*", message = "Tags are comma separated and each tag must start ISO basic Latin letter")
    private String includedTags;

    @Schema(description = "Tasks with these tags are to be excluded", example = "bedroom")
    @Column(columnDefinition = "text")
    @Pattern(regexp = "([^\\W\\d_][^,]*)(,([^\\W\\d_][^,]*))*", message = "Tags are comma separated and each tag must start ISO basic Latin letter")
    private String excludedTags;
}
