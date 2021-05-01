package rafinha.example.sfgwebstats.services;

import rafinha.example.sfgwebstats.model.Match;

import java.util.Set;

public interface MatchService extends CrudService<Match, Long> {

    Set<Match> findByClubName(String clubName);
}
