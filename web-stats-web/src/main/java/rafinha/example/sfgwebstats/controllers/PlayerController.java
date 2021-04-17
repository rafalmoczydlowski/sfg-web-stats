package rafinha.example.sfgwebstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlayerController {

    @GetMapping({"players", "players/index", "players/index.html"})
    public String listPlayers() {
        return "players/index";
    }
}
