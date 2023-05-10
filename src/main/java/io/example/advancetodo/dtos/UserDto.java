package io.example.advancetodo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class UserDto {
    @Schema(description = "User id", example = "13")
    private Long id;

    @Schema(description = "User login", example = "jonny13")
    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 63)
    @NotBlank
    private String login;

    @Schema(description = "User e-mail", example = "jon.doe@example.io")
    @Column(unique = true)
    @Email
    @Size(max = 255)
    private String mail;

    @Schema(description = "List of its tasks list")
    List<Long> itsLists;

    @Schema(description = "Lists that are shared with its")
    List<Long> listsSharedIts; // niechaj ta linijka stanowi dla mnie pomnik "ku pamięci", jak to drobna nieuwaga potrafi ukraść tak wiele godzin życia
}
