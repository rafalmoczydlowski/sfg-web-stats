package rafinha.example.sfgwebstats.repositories;

import org.springframework.data.repository.CrudRepository;
import rafinha.example.sfgwebstats.model.Player;

import java.util.Set;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    Player findByLastName(String lastName);

    Set<Player> findAllByClub(String club);

    Set<Player> findAllByClubId(Long clubId);
}
