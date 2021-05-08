package rafinha.example.sfgwebstats.services.springdatajpa;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Match;
import rafinha.example.sfgwebstats.repositories.MatchRepository;
import rafinha.example.sfgwebstats.services.MatchService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class MatchJpaService implements MatchService {

    private final MatchRepository matchRepository;

    public MatchJpaService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Set<Match> findByHostClub(Club club) {
        return matchRepository.findByHostClub(club);
    }

    @Override
    public Set<Match> findByVisitorClub(Club club) {
        return matchRepository.findByHostClub(club);
    }

    @Override
    public Set<Match> findAll() {
        Set<Match> matchSet = new HashSet<>();
        matchRepository.findAll().forEach(matchSet::add);
        return matchSet;
    }

    @Override
    public Match findById(Long aLong) {
        return matchRepository.findById(aLong).orElse(null);
    }

    @Override
    public Match save(Match object) {
        return matchRepository.save(object);
    }

    @Override
    public void delete(Match object) {
        matchRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        matchRepository.deleteById(aLong);
    }
}
