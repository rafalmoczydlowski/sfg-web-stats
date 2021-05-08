package rafinha.example.sfgwebstats.repositories;

import org.springframework.data.repository.CrudRepository;
import rafinha.example.sfgwebstats.model.PlayerType;

public interface PlayerTypeRepository extends CrudRepository<PlayerType, Long> {
}
