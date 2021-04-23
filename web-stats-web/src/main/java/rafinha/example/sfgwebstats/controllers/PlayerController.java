package rafinha.example.sfgwebstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rafinha.example.sfgwebstats.services.PlayerService;

@RequestMapping("/players")
@Controller
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String listPlayers(Model model) {
        model.addAttribute("players", playerService.findAll());
        return "players/index";
    }
}
