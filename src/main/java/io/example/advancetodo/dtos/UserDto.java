package io.example.advancetodo.dtos;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto {
    @Size(min = 3, max = 63)
    @UniqueElements
    @NotBlank
    private String login;

    @Email
    private String mail;
}
