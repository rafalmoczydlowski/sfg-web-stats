package rafinha.example.sfgwebstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.services.ClubService;

import java.util.List;

@RequestMapping("/clubs")
@Controller
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping({"/find"})
    public String findClubs(Model model) {
        model.addAttribute("club", Club.builder().build());
        return "clubs/findClubs";
    }

    @GetMapping
    public String showFindForm(Club club, BindingResult result, Model model) {
        if (club.getName() == null) {
            club.setName("");
        }

        List<Club> results = clubService.findAllByNameLike(club.getName().toLowerCase());

        if(results.isEmpty()) {
            result.rejectValue("name", "notFound", "not found");
            return "clubs/findClubs";
        }
        else if (results.size() == 1) {
            club = results.get(0);
            return "redirect:/clubs/" + club.getId();
        }
        else {
            model.addAttribute("selections", results);
            return "clubs/clubsList";
        }
    }

    @GetMapping("/{clubId}")
    public ModelAndView showClub(@PathVariable("clubId") Long clubId) {
        ModelAndView modelAndView = new ModelAndView("clubs/clubDetails");
        modelAndView.addObject(this.clubService.findById(clubId));
        return modelAndView;
    }
}
