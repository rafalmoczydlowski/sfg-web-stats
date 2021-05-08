package rafinha.example.sfgwebstats.services;

import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Match;

import java.util.Set;

public interface MatchService extends CrudService<Match, Long> {

    Set<Match> findByHostClub(Club club);

    Set<Match> findByVisitorClub(Club club);
}
