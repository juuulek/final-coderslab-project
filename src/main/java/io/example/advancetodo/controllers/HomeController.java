package io.example.advancetodo.controllers;

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

        StringBuilder response = new StringBuilder();
        response.append("<p>\n");
        response.append(user.toHtml());
        response.append("</p>\n");


        response.append("<p>\n");
        response.append("Własne listy:");
        if (user.getItsLists() == null)
            response.append("brak");
        else
            for (TaskList itsList : user.getItsLists())
                response.append("<div>" + itsList.getName() + "</div>");
        response.append("</p>\n");


        response.append("<p>\n");
        response.append("Udostępnione listy:");
        if (user.getListsSharedIts() == null)
            response.append("brak");
        else
            for (TaskList listSharedIts : user.getListsSharedIts())
                response.append("<div>" + listSharedIts.getName() + "</div>");
        response.append("</p>\n");

        return response.toString();
    }
}
