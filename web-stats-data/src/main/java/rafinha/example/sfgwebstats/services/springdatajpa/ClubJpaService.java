package rafinha.example.sfgwebstats.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Coach;
import rafinha.example.sfgwebstats.repositories.ClubRepository;
import rafinha.example.sfgwebstats.services.ClubService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class ClubJpaService implements ClubService {

    private final ClubRepository clubRepository;

    public ClubJpaService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public Club findClubByCoach(Coach coach) {
        return clubRepository.findClubByCoach(coach);
    }

    @Override
    public List<Club> findAllByNameLike(String name) {
        return clubRepository.findClubsByName(name);
    }

    @Override
    public Set<Club> findAll() {
        Set<Club> clubSet = new HashSet<>();
        clubRepository.findAll().forEach(clubSet::add);
        return clubSet;
    }

    @Override
    public Club findById(Long aLong) {
        return clubRepository.findById(aLong).orElse(null);
    }

    @Override
    public Club save(Club object) {
        return clubRepository.save(object);
    }

    @Override
    public void delete(Club object) {
        clubRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        clubRepository.deleteById(aLong);
    }
}
