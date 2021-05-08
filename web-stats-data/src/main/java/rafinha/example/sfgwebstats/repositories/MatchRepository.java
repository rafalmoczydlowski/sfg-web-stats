package rafinha.example.sfgwebstats.repositories;

import org.springframework.data.repository.CrudRepository;
import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Match;

import java.util.Set;

public interface MatchRepository extends CrudRepository<Match, Long> {

    Set<Match> findByClub(Club club);
}
