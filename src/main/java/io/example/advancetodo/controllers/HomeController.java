package io.example.advancetodo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class HomeController {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        Random random = new Random();
        int x = random.nextInt();
        long y = random.nextInt();
        return "Witaj!<br \\>" +
                "Tu niestety jeszcze nic nie ma :(<br \\>" +
                "A wiedziałaś/łeś, że " + x + " × " + y + " = " + (x * y);
    }
}
