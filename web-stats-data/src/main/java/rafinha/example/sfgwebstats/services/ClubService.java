package rafinha.example.sfgwebstats.services;

import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Coach;
import rafinha.example.sfgwebstats.model.Player;

import java.util.Set;

public interface ClubService extends CrudService<Club, Long> {

    Set<Player> findClubPlayers(String clubName);

    Coach findClubCoach(String clubName);
}