package rafinha.example.sfgwebstats.services.map;

import org.springframework.stereotype.Service;
import rafinha.example.sfgwebstats.model.Player;
import rafinha.example.sfgwebstats.services.PlayerService;

import java.util.Collections;
import java.util.Set;

@Service
public class PlayerServiceMap extends AbstractMapService<Player, Long> implements PlayerService {
    @Override
    public Player findByLastName(String lastName) {
        return null;
    }

    @Override
    public Set<Player> findAllByClub(String clubName) {
        return Collections.emptySet();
    }

    @Override
    public Set<Player> findAll() {
        return super.findAll();
    }

    @Override
    public Player findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void delete(Player object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Player save(Player object) {
        return super.save(object);
    }
}
