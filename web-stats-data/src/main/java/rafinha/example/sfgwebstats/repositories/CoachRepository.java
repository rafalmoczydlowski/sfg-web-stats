package rafinha.example.sfgwebstats.repositories;

import org.springframework.data.repository.CrudRepository;
import rafinha.example.sfgwebstats.model.Coach;

public interface CoachRepository extends CrudRepository<Coach, Long> {

    Coach findByLastName(String lastName);

    Coach findCoachByClub(String clubName);
}
