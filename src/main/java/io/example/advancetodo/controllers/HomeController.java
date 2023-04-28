package io.example.advancetodo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class HomeController {
    @ResponseBody
    @RequestMapping
    String home() {
        Random random = new Random();
        int x = random.nextInt();
        long y = random.nextInt();
        return "Witaj!<br \\>" +
                "Tu niestety jeszcze nic nie ma :(<br \\>" +
                "A wiedziałaś/łeś, że " + x + " × " + y + " = " + (x * y);
    }

    @ResponseBody
    @RequestMapping("/{user}")
    public String checkUser(@PathVariable String user) {
        return "Sorry! Jeszcze nie umiem sprawdzić, czy użytkownik <i>" + user + "</i> istnieje :(";
    }
    // tu dorobię odpytanie GET zwracające na przeglądarkę, czy dany user istnieje
    // w następnym kroku będzie zwracał listy zadań tego usera i/lub zadania mu udostępnione, i/lub zadanie z jego filtrów
    // potem pozostanie nauka Spring Security
}
