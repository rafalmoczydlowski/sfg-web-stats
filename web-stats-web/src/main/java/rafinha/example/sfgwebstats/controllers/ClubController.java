package rafinha.example.sfgwebstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rafinha.example.sfgwebstats.services.ClubService;

@RequestMapping("/clubs")
@Controller
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String listClubs(Model model) {
        model.addAttribute("clubs", clubService.findAll());
        return "clubs/index";
    }
}
