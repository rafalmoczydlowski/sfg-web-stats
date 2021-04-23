package rafinha.example.sfgwebstats.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Coach;
import rafinha.example.sfgwebstats.model.Player;
import rafinha.example.sfgwebstats.services.ClubService;
import rafinha.example.sfgwebstats.services.CoachService;
import rafinha.example.sfgwebstats.services.PlayerService;

@Component
public class DataInit implements CommandLineRunner {

    private final PlayerService playerService;
    private final CoachService coachService;
    private final ClubService clubService;

    public DataInit(PlayerService playerService, CoachService coachService, ClubService clubService) {
        this.playerService = playerService;
        this.coachService = coachService;
        this.clubService = clubService;
    }

    @Override
    public void run(String... args) throws Exception {
        Club fcBarcelona = new Club();
        fcBarcelona.setName("FC Barcelona");

        clubService.save(fcBarcelona);

        System.out.println("Loaded Clubs...");

        Player player1 = new Player();
        player1.setPosition("GK");
        player1.setClub(fcBarcelona);
        player1.setShirtNumber(1);
        player1.setAge(25);
        player1.setFirstName("Rafał");
        player1.setLastName("Moczydłowski");

        playerService.save(player1);

        Player player2 = new Player();
        player2.setPosition("FW");
        player2.setClub(fcBarcelona);
        player2.setShirtNumber(10);
        player2.setAge(33);
        player2.setFirstName("Lionel");
        player2.setLastName("Messi");

        playerService.save(player2);


        System.out.println("Loaded Players...");

        Coach coach1 = new Coach();
        coach1.setClub(fcBarcelona);
        coach1.setAge(55);
        coach1.setFirstName("Ronald");
        coach1.setLastName("Koeman");

        coachService.save(coach1);

        fcBarcelona.setCoach(coach1);
        fcBarcelona.setPlayers(player1, player2);

        System.out.println("Loaded Coaches...");
    }
}
