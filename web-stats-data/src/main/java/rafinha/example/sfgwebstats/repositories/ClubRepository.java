package rafinha.example.sfgwebstats.repositories;

import org.springframework.data.repository.CrudRepository;
import rafinha.example.sfgwebstats.model.Club;

public interface ClubRepository extends CrudRepository<Club, Long> {
}
