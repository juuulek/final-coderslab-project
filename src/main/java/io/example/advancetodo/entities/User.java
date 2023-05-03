package io.example.advancetodo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 63)
    @NotBlank
    private String login;

    @Column(unique = true)
    @Email
    @Size(max = 255)
    private String mail;

    // logowaniem i hasłami użytkowników dziś się nie zajmuję
    // na ten moment "logowanie" będzie odbywało się bez żadnej autoryzacji;
    // Spring Security jest przygotowany w pom-ie, ale nie zakładam nauczenia się go przed obroną projektu
}
