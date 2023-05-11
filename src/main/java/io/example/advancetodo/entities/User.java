package io.example.advancetodo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "owner")
    List<TaskList> itsLists = new ArrayList<>();

    @ManyToMany(mappedBy = "shared")
    List<TaskList> listsSharedIts = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    List<ListFilter> filters = new ArrayList<>();

    public User(Long id) {
        this.id = id;
    }

    public String toHtml() {
        return "<div class=\"login odd\">login:\t" + login + "</div>\n" +
                "<div class=\"mail even\">e-mail:\t" + mail + "</div>\n" +
                "<div class=\"id odd\">id:\t" + id + "</div>";
    }

    // logowaniem i hasłami użytkowników dziś się nie zajmuję
    // na ten moment "logowanie" będzie odbywało się bez żadnej autoryzacji;
    // Spring Security jest przygotowany w pom-ie, ale nie zakładam nauczenia się go przed obroną projektu
}
