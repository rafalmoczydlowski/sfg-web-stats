package rafinha.example.sfgwebstats.repositories;

import org.springframework.data.repository.CrudRepository;
import rafinha.example.sfgwebstats.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {
}
