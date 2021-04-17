package rafinha.example.sfgwebstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/clubs")
@Controller
public class ClubController {

    @GetMapping({"", "/", "/index", "/index.html"})
    public String listClubs() {
        return "clubs/index";
    }
}
