package io.example.advancetodo.controllers;

import io.example.advancetodo.entities.ListFilter;
import io.example.advancetodo.entities.TaskList;
import io.example.advancetodo.entities.User;
import io.example.advancetodo.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@AllArgsConstructor
@Hidden // nie ma sensu, żeby ten webowy kontroler był widoczny w swaggerze
public class HomeController {
    private final UserRepository userRepository;

    @ResponseBody
    @RequestMapping
    String home() {
        Random random = new Random();
        int x = random.nextInt();
        long y = random.nextInt();

        return "<!DOCTYPE html>\n" +
                "<html lang=\"pl\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>to do</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<p>\n" +
                "    Witaj!\n" +
                "</p>\n" +
                "<p>\n" +
                "    Jest nas już " + userRepository.count() + "!\n" +
                "</p>\n" +
                "<form method=\"get\" action=\"/form\">\n" +
                "    <label>\n" +
                "        login lub e-mail: <input type=\"text\" required name=\"nick\">\n" +
                "    </label>\n" +
                "    <input type=\"submit\">\n" +
                "</form>\n" +
                "<p>\n" +
                "    A wiedziałaś/łeś, że " + x + " × " + y + " = " + (x * y) + "?" +
                "</p>\n" +
                "</body>\n" +
                "</html>";
    }

    @RequestMapping("/form")
    public String redirectFromForm(@RequestParam String nick) {
        return "redirect:/" + nick;
    }

    @ResponseBody
    @RequestMapping("/{nick}")
    public String checkUser(@PathVariable String nick) {
        User user;

        if (userRepository.existsByLogin(nick))
            user = userRepository.findByLogin(nick).get();
        else if (userRepository.existsByMail(nick))
            user = userRepository.findByMail(nick).get();
        else
            return "Użytkownik " + nick + " nie istnieje";

        // nagłówek, style, przywitanie
        StringBuilder response = new StringBuilder("<!DOCTYPE html>\n" +
                "<html lang=\"pl\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>to do — " + user.getLogin() + "</title>\n" +
                "    <style>\n" +
                "        .odd {\n" +
                "            background: #eee;\n" +
                "        }\n" +
                "        .id {\n" +
                "            font-size: smaller;\n" +
                "        }\n" +
                "        div,.list {\n" +
                "            margin-left: 20px;\n" +
                "        }\n" +
                "        .done {\n" +
                "            color: dimgray;\n" +
                "            text-decoration: line-through;\n" +
                "        }\n" +
                "        .not_appeared {\n" +
                "            color: dimgray;\n" +
                "            font-style: italic;\n" +
                "        }\n" +
                "        .alert {\n" +
                "            color: yellow;\n" +
                "        }\n" +
                "        .deadline {\n" +
                "            color: red;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n\n" +
                "<body>\n" +
                "<p>Witaj " + user.getLogin() + "!</p>\n\n");

        // szczegóły użytkownika
        response.append("<details>\n" +
                "    <summary>Szczegóły użytkownika</summary>\n" +
                "    <div class=\"login odd\">login:\t" + user.getLogin() + "</div>\n" +
                "    <div class=\"mail even\">e-mail:\t" + user.getMail() + "</div>\n" +
                "    <div class=\"id odd\">id:\t" + user.getId() + "</div>\n" +
                "</details>\n\n");

        // listy własnych zadań
        response.append("<details>\n" +
                "    <summary>Własne zadania</summary>\n");
        if (user.getItsLists() == null || user.getItsLists().isEmpty())
            response.append("        <div>" + user.getLogin() + " nie posiada żadnych własnych list zadań!</div>\n");
        else
            for (TaskList list : user.getItsLists())
                response.append(list.toHtml());
        response.append("</details>\n\n");

        // udostępnione listy zadań
        response.append("<details>\n" +
                "    <summary>Udostępnione zadania</summary>\n");
        if (user.getListsSharedIts() == null || user.getListsSharedIts().isEmpty())
            response.append("        <div>Użytkownikowi " + user.getLogin() + " nie udostępniono żadnych list zadań!</div>\n");
        else
            for (TaskList list : user.getListsSharedIts())
                response.append(list.toHtml());
        response.append("</details>\n\n");

        // filtry zadań
        response.append("<details>\n" +
                "    <summary>Filtry zadań</summary>\n");
        if (user.getFilters() == null || user.getFilters().isEmpty())
            response.append("        <div>" + user.getLogin() + " nie posiada żadnych filtrów zadań!</div>\n");
        else
            for (ListFilter filter : user.getFilters())
                response.append(filter.toHtml());
        response.append("</details>\n\n");

        response.append("</body>\n" +
                "</html>");
        return response.toString();
    }
}
