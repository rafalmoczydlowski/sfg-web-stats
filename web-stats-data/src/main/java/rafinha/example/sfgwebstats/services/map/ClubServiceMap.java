package rafinha.example.sfgwebstats.services.map;

import org.springframework.stereotype.Service;
import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Coach;
import rafinha.example.sfgwebstats.model.Player;
import rafinha.example.sfgwebstats.services.ClubService;

import java.util.Collections;
import java.util.Set;

@Service
public class ClubServiceMap extends AbstractMapService<Club, Long> implements ClubService {
    @Override
    public Set<Player> findClubPlayers(String clubName) {
        return Collections.emptySet();
    }

    @Override
    public Coach findClubCoach(String clubName) {
        return null;
    }

    @Override
    public Set<Club> findAll() {
        return super.findAll();
    }

    @Override
    public Club findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void delete(Club object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Club save(Club object) {
        return super.save(object);
    }
}
