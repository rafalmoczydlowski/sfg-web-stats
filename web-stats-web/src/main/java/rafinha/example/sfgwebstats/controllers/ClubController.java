package rafinha.example.sfgwebstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClubController {

    @GetMapping({"/clubs", "/clubs/index", "/clubs/index.html"})
    public String listClubs() {
        return "clubs/index";
    }
}
