package rafinha.example.sfgwebstats.services;

import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Player;

import java.util.Set;

public interface PlayerService extends CrudService<Player, Long> {

    Player findByLastName(String lastName);

    Set<Player> findAllByClub(Club club);
}
