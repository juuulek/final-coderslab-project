package io.example.advancetodo.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto {
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 63)
    @NotBlank
    private String login;

    @Column(unique = true)
    @Email
    private String mail;
}
