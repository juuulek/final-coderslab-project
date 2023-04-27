package io.example.advancetodo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private String login;

    // logowaniem i hasłami użytkowników dziś się nie zajmuję
    // na ten moment "logowanie" będzie odbywało się bez żadnej autoryzacji;
    // Spring Security jest przygotowany w pomie, ale nie zakładam nauczenia się go przed obroną
}
