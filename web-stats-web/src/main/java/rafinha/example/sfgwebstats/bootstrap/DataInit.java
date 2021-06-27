package rafinha.example.sfgwebstats.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rafinha.example.sfgwebstats.model.*;
import rafinha.example.sfgwebstats.services.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
        Club realMadryt = new Club();

        fcBarcelona.setName("FC Barcelona");
        fcBarcelona.setYearOfEstablishment(LocalDate.of(1899, 11, 29));

        realMadryt.setName("Real Madryt");

        clubService.save(fcBarcelona);
        clubService.save(realMadryt);

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

        Player player1 = new Player();
        player1.setPosition("GK");
        player1.setClub(fcBarcelona);
        player1.setShirtNumber(1);
        player1.setAge(25);
        player1.setFirstName("Rafał");
        player1.setLastName("Moczydłowski");
        player1.getPlayerTypeSet().add(savedGoalkeeper);

        Player player2 = new Player();
        player2.setPosition("FW");
        player2.setClub(fcBarcelona);
        player2.setShirtNumber(10);
        player2.setAge(33);
        player2.setFirstName("Lionel");
        player2.setLastName("Messi");
        player2.getPlayerTypeSet().add(savedAttacking);

        Player player3 = new Player();
        player3.setPosition("MF");
        player3.setClub(realMadryt);
        player3.setShirtNumber(10);
        player3.setAge(35);
        player3.setFirstName("Luka");
        player3.setLastName("Modric");
        player3.getPlayerTypeSet().add(savedMaestro);

        playerService.save(player1);
        playerService.save(player2);
        playerService.save(player3);

        Coach coach1 = new Coach();
        coach1.setClub(fcBarcelona);
        coach1.setAge(55);
        coach1.setFirstName("Ronald");
        coach1.setLastName("Koeman");

        Coach coach2 = new Coach();
        coach2.setClub(realMadryt);
        coach2.setAge(55);
        coach2.setFirstName("Zinedine");
        coach2.setLastName("Zidane");

        coachService.save(coach1);
        coachService.save(coach2);

        fcBarcelona.setCoach(coach1);
        fcBarcelona.setPlayers(player1, player2);
        realMadryt.setCoach(coach2);
        realMadryt.setPlayers(player3);

        LocalDate elClasicoDate = LocalDate.of(2021, 04, 21);
        Match elClasico = new Match();
        elClasico.setPlayDate(elClasicoDate);
        elClasico.setHostClub(realMadryt);
        elClasico.setVisitorClub(fcBarcelona);
        elClasico.setScore("3:1");

        Set<Match> fcBarcelonaMatchSet = new HashSet<>();
        fcBarcelonaMatchSet.add(elClasico);
        fcBarcelona.setMatchSet(fcBarcelonaMatchSet);

        matchService.save(elClasico);

        clubService.save(fcBarcelona);
        clubService.save(realMadryt);
    }
}
