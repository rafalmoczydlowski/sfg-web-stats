package rafinha.example.sfgwebstats.repositories;

import org.springframework.data.repository.CrudRepository;
import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Coach;

import java.util.List;

public interface ClubRepository extends CrudRepository<Club, Long> {

    Club findClubByCoach(Coach coach);

    List<Club> findClubsByNameContainingIgnoreCase(String name);

}
