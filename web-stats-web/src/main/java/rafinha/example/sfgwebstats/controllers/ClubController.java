package rafinha.example.sfgwebstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.services.ClubService;
import rafinha.example.sfgwebstats.services.PlayerService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/clubs")
@Controller
public class ClubController {
    private static final String CLUB_UPDATE_CREATE_FORM_VIEWNAME = "clubs/createOrUpdateForm";
    private static final String CLUB_REDIRECT_VIEWNAME = "redirect:/clubs/";

    private final ClubService clubService;
    private final PlayerService playerService;

    public ClubController(ClubService clubService, PlayerService playerService) {
        this.clubService = clubService;
        this.playerService = playerService;
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
            return CLUB_REDIRECT_VIEWNAME + club.getId();
        }
        else {
            model.addAttribute("selections", results);
            return "clubs/clubsList";
        }
    }

    @GetMapping("/{clubId}")
    public ModelAndView showClub(@PathVariable Long clubId) {
        ModelAndView modelAndView = new ModelAndView("clubs/clubDetails");
        modelAndView.addObject(this.clubService.findById(clubId));
        return modelAndView;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("club", Club.builder().build());
        return CLUB_UPDATE_CREATE_FORM_VIEWNAME;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Club club, BindingResult result) {
        if (result.hasErrors())
            return CLUB_UPDATE_CREATE_FORM_VIEWNAME;
        else {
            Club savedClub = clubService.save(club);
            return CLUB_REDIRECT_VIEWNAME + savedClub.getId();
        }
    }

    @GetMapping("/{clubId}/edit")
    public String initUpdateForm(@PathVariable Long clubId, Model model) {
        model.addAttribute(clubService.findById(clubId));
        return CLUB_UPDATE_CREATE_FORM_VIEWNAME;
    }

    @PostMapping("/{clubId}/edit")
    public String processUpdateForm(@PathVariable Long clubId, @Valid Club club, BindingResult result) {
        if (result.hasErrors())
            return CLUB_UPDATE_CREATE_FORM_VIEWNAME;
        else {
            club.setId(clubId);
            Club savedClub = clubService.save(club);
            return CLUB_REDIRECT_VIEWNAME + savedClub.getId();
        }
    }

    @GetMapping("/{clubId}/players")
    public String listAllPlayersFromClub(@PathVariable Long clubId, Model model) {
        model.addAttribute("club", clubService.findById(clubId));
        model.addAttribute("players", playerService.findAllByClubId(clubId));
        return "clubs/listOfClubPlayers";
    }
}
