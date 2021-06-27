package rafinha.example.sfgwebstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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

    @GetMapping({"/find"})
    public String findClubs() {
        return "notimplemented";
    }

    @GetMapping("/{clubId}")
    public ModelAndView showClub(@PathVariable("clubId") Long clubId) {
        ModelAndView modelAndView = new ModelAndView("clubs/clubDetails");
        modelAndView.addObject(this.clubService.findById(clubId));
        return modelAndView;
    }
}
