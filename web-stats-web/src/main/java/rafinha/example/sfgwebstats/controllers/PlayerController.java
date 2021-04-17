package rafinha.example.sfgwebstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/players")
@Controller
public class PlayerController {

    @GetMapping({"", "/", "/index", "/index.html"})
    public String listPlayers() {
        return "players/index";
    }
}
