package rafinha.example.sfgwebstats.services.map;

import org.springframework.stereotype.Service;
import rafinha.example.sfgwebstats.model.PlayerType;
import rafinha.example.sfgwebstats.services.PlayerTypeService;

import java.util.Set;

@Service
public class PlayerTypeMapService extends AbstractMapService<PlayerType, Long> implements PlayerTypeService {
    @Override
    public Set<PlayerType> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(PlayerType object) {
        super.delete(object);
    }

    @Override
    public PlayerType save(PlayerType object) {
        return super.save(object);
    }

    @Override
    public PlayerType findById(Long id) {
        return super.findById(id);
    }
}
