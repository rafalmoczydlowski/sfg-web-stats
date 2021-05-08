package rafinha.example.sfgwebstats.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rafinha.example.sfgwebstats.model.Coach;
import rafinha.example.sfgwebstats.repositories.CoachRepository;
import rafinha.example.sfgwebstats.services.CoachService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class CoachJpaService implements CoachService {

    private final CoachRepository coachRepository;

    public CoachJpaService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @Override
    public Coach findByLastName(String lastName) {
        return coachRepository.findByLastName(lastName);
    }

    @Override
    public Coach findCoachByClubName(String clubName) {
        return coachRepository.findCoachByClub(clubName);
    }

    @Override
    public Set<Coach> findAll() {
        Set<Coach> coachSet = new HashSet<>();
        coachRepository.findAll().forEach(coachSet::add);
        return coachSet;
    }

    @Override
    public Coach findById(Long aLong) {
        return coachRepository.findById(aLong).orElse(null);
    }

    @Override
    public Coach save(Coach object) {
        return coachRepository.save(object);
    }

    @Override
    public void delete(Coach object) {
        coachRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        coachRepository.deleteById(aLong);
    }
}
