package rafinha.example.sfgwebstats.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Coach;
import rafinha.example.sfgwebstats.model.Player;
import rafinha.example.sfgwebstats.services.CoachService;
import rafinha.example.sfgwebstats.services.PlayerService;

@Component
public class DataInit implements CommandLineRunner {

    private final PlayerService playerService;
    private final CoachService coachService;

    public DataInit(PlayerService playerService, CoachService coachService) {
        this.playerService = playerService;
        this.coachService = coachService;
    }

    @Override
    public void run(String... args) throws Exception {
        Club fcBarcelona = new Club();
        fcBarcelona.setName("FC Barcelona");

        Player player1 = new Player();
        player1.setId(1L);
        player1.setClub(fcBarcelona);
        player1.setPosition("GK");
        player1.setShirtNumber(1);
        player1.setAge(25);
        player1.setFirstName("Rafał");
        player1.setLastName("Moczydłowski");

        playerService.save(player1);

        Player player2 = new Player();
        player1.setId(2L);
        player1.setClub(fcBarcelona);
        player1.setPosition("FW");
        player1.setShirtNumber(10);
        player1.setAge(33);
        player1.setFirstName("Lionel");
        player1.setLastName("Messi");

        playerService.save(player2);

        System.out.println("Loaded Players...");

        Coach coach1 = new Coach();
        coach1.setId(1L);
        coach1.setClub(fcBarcelona);
        coach1.setAge(55);
        coach1.setFirstName("Ronald");
        coach1.setLastName("Koeman");

        coachService.save(coach1);

        System.out.println("Loaded Coaches...");
    }
}
