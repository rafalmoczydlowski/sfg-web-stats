package rafinha.example.sfgwebstats.services.map;

import org.springframework.stereotype.Service;
import rafinha.example.sfgwebstats.model.Coach;
import rafinha.example.sfgwebstats.services.CoachService;

import java.util.Set;

@Service
public class CoachServiceMap extends AbstractMapService<Coach, Long> implements CoachService {
    @Override
    public Coach findByLastName(String lastName) {
        return null;
    }

    @Override
    public Coach findCoachByClubName(String clubName) {
        return null;
    }

    @Override
    public Set<Coach> findAll() {
        return super.findAll();
    }

    @Override
    public Coach findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void delete(Coach object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Coach save(Coach object) {
        return super.save(object);
    }
}
