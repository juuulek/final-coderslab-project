package io.example.advancetodo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 63)
    @UniqueElements
    @NotBlank
    private String login;

    @Email
    private String mail;

    // logowaniem i hasłami użytkowników dziś się nie zajmuję
    // na ten moment "logowanie" będzie odbywało się bez żadnej autoryzacji;
    // Spring Security jest przygotowany w pomie, ale nie zakładam nauczenia się go przed obroną
}
