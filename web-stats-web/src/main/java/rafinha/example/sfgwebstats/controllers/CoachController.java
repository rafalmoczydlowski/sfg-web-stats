package rafinha.example.sfgwebstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rafinha.example.sfgwebstats.services.CoachService;

@RequestMapping("/coaches")
@Controller
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String getCoaches(Model model){
        model.addAttribute("coaches", coachService.findAll());
        return "coaches/index";
    }
}
