package rafinha.example.sfgwebstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Player;
import rafinha.example.sfgwebstats.services.ClubService;
import rafinha.example.sfgwebstats.services.PlayerService;

import javax.validation.Valid;

@RequestMapping("/players")
@Controller
public class PlayerController {

    private final PlayerService playerService;
    private final ClubService clubService;

    public PlayerController(PlayerService playerService, ClubService clubService) {
        this.playerService = playerService;
        this.clubService = clubService;
    }

    @GetMapping({"/", "/index", "/index.html"})
    public String listPlayers(Model model) {
        model.addAttribute("players", playerService.findAll());
        return "players/index";
    }

    @GetMapping("clubs/{clubId}/player/new")
    public String initCreationForm(@PathVariable Long clubId, Club club, Model model) {
        Player player = new Player();
        club.setId(clubId);
        club.getPlayerSet().add(player);
        model.addAttribute("club", club);
        model.addAttribute("player", player);
        return "players/createOrUpdateForm";
    }

    @PostMapping("clubs/{clubId}/player/new")
    public String processCreationForm(@PathVariable Long clubId, Club club, @Valid Player player, BindingResult result, ModelMap model) {
        if(StringUtils.hasLength(player.getFullName()) && player.isNew() && club.getPlayer(player.getFullName(), true) != null)
            result.rejectValue("name", "duplicate", "already exists");

        club.getPlayerSet().add(player);

        if(result.hasErrors()) {
            model.put("player", player);
            return "players/createOrUpdateForm";
        }
        else {
            club.setId(clubId);
            playerService.save(player);
            return "redirect:/clubs/" + club.getId();
        }
    }

    @GetMapping("clubs/{clubId}/player/{playerId}/edit")
    public String initUpdateForm(@PathVariable Long clubId, @PathVariable Long playerId, Model model) {
        model.addAttribute("club", clubService.findById(clubId));
        model.addAttribute("player", playerService.findById(playerId));
        return "players/createOrUpdateForm";
    }

    @PostMapping("clubs/{clubId}/player/{playerId}/edit")
    public String processUpdateForm(@Valid Player player,
                                    @PathVariable Long clubId,
                                    @PathVariable Long playerId,
                                    BindingResult result,
                                    Club club,
                                    Model model) {
        if (result.hasErrors()) {
            player.setClub(club);
            model.addAttribute("player", playerService.findById(playerId));
            model.addAttribute("club", clubService.findById(clubId));
            return "players/createOrUpdateForm";
        }
        else {
            club.setId(clubId);
            club.getPlayerSet().add(player);
            playerService.save(player);
            return "redirect:/clubs/" + club.getId();
        }
    }

}
