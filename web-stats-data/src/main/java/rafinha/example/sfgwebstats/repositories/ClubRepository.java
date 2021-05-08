package rafinha.example.sfgwebstats.repositories;

import org.springframework.data.repository.CrudRepository;
import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Coach;

public interface ClubRepository extends CrudRepository<Club, Long> {

    Club findClubByCoach(Coach coach);

}
