package rafinha.example.sfgwebstats.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Match;
import rafinha.example.sfgwebstats.services.MatchService;

import java.util.Collections;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class MatchMapService extends AbstractMapService<Match, Long> implements MatchService {
    @Override
    public Set<Match> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Match object) {
        super.delete(object);
    }

    @Override
    public Match save(Match object) {
        return super.save(object);
    }

    @Override
    public Match findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Match> findByHostClub(Club club) {
        return Collections.emptySet();
    }

    @Override
    public Set<Match> findByVisitorClub(Club club) {
        return Collections.emptySet();
    }
}
