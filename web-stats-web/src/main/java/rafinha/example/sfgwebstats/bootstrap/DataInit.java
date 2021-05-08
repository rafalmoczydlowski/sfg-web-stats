package rafinha.example.sfgwebstats.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rafinha.example.sfgwebstats.model.*;
import rafinha.example.sfgwebstats.services.*;

import java.time.LocalDate;

@Component
public class DataInit implements CommandLineRunner {

    private final PlayerService playerService;
    private final CoachService coachService;
    private final ClubService clubService;
    private final MatchService matchService;
    private final PlayerTypeService playerTypeService;

    public DataInit(PlayerService playerService,
                    CoachService coachService,
                    ClubService clubService,
                    MatchService matchService,
                    PlayerTypeService playerTypeService) {
        this.playerService = playerService;
        this.coachService = coachService;
        this.clubService = clubService;
        this.matchService = matchService;
        this.playerTypeService = playerTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = playerService.findAll().size();
        if(count == 0)
            initData();
    }

    private void initData() {
        Club fcBarcelona = new Club();
        fcBarcelona.setName("FC Barcelona");

        Club realMadryt = new Club();
        realMadryt.setName("Real Madryt");

        PlayerType attacking = new PlayerType();
        attacking.setDescription("A player whose main task is to attack the opponent's goal and score goals.");
        PlayerType savedAttacking = playerTypeService.save(attacking);

        PlayerType maestro = new PlayerType();
        maestro.setDescription("A player whose main task is to create action through creative passes, technical tricks.");
        PlayerType savedMaestro = playerTypeService.save(maestro);

        PlayerType butcher = new PlayerType();
        butcher.setDescription("A player whose main task is to interrupt the opponent's actions as far as possible.");
        PlayerType savedButcher = playerTypeService.save(butcher);

        PlayerType goalkeeper = new PlayerType();
        butcher.setDescription("A player whose main task is to defend the opponent's shots");
        PlayerType savedGoalkeeper = playerTypeService.save(goalkeeper);

        clubService.save(fcBarcelona);
        clubService.save(realMadryt);

        System.out.println("Loaded Clubs...");

        Player player1 = new Player();
        player1.setPosition("GK");
        player1.setClub(fcBarcelona);
        player1.setShirtNumber(1);
        player1.setAge(25);
        player1.setFirstName("Rafał");
        player1.setLastName("Moczydłowski");
        player1.getPlayerTypeSet().add(savedGoalkeeper);

        playerService.save(player1);

        Player player2 = new Player();
        player2.setPosition("FW");
        player2.setClub(fcBarcelona);
        player2.setShirtNumber(10);
        player2.setAge(33);
        player2.setFirstName("Lionel");
        player2.setLastName("Messi");
        player2.getPlayerTypeSet().add(savedAttacking);

        playerService.save(player2);

        Player player3 = new Player();
        player3.setPosition("MF");
        player3.setClub(realMadryt);
        player3.setShirtNumber(10);
        player3.setAge(35);
        player3.setFirstName("Luka");
        player3.setLastName("Modric");
        player3.getPlayerTypeSet().add(savedMaestro);

        playerService.save(player3);

        System.out.println("Loaded Players...");

        Coach coach1 = new Coach();
        coach1.setClub(fcBarcelona);
        coach1.setAge(55);
        coach1.setFirstName("Ronald");
        coach1.setLastName("Koeman");

        coachService.save(coach1);

        Coach coach2 = new Coach();
        coach2.setClub(realMadryt);
        coach2.setAge(55);
        coach2.setFirstName("Zinedine");
        coach2.setLastName("Zidane");

        coachService.save(coach2);

        System.out.println("Loaded Coaches...");

        fcBarcelona.setCoach(coach1);
        fcBarcelona.setPlayers(player1, player2);
        realMadryt.setCoach(coach2);
        realMadryt.setPlayers(player3);

        System.out.println("Creating match ---------");

        LocalDate elClasicoDate = LocalDate.of(2021, 04, 21);
        Match elClasico = new Match();
        elClasico.setPlayDate(elClasicoDate);
        elClasico.setHostClub(realMadryt);
        elClasico.setVisitorClub(fcBarcelona);
        elClasico.setScore("3:1");

        matchService.save(elClasico);

        System.out.println("Loaded Matches...");
        System.out.println("El Clasico is a match between: " + elClasico.getHostClub().getName() + " and " + elClasico.getVisitorClub().getName());
        System.out.println("The last El Clasico took place " + elClasico.getPlayDate() + " and it ended with a result " + elClasico.getScore());
    }
}
