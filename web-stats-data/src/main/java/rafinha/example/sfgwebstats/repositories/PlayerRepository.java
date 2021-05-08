package rafinha.example.sfgwebstats.repositories;

import org.springframework.data.repository.CrudRepository;
import rafinha.example.sfgwebstats.model.Player;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}
