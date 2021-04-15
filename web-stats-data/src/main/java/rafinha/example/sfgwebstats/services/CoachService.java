package rafinha.example.sfgwebstats.services;

import rafinha.example.sfgwebstats.model.Coach;

import java.util.Set;

public interface CoachService {

    Coach findByLastName(String lastName);

    Coach findById(Long id);

    Coach sava(Coach coach);

    Set<Coach> findAll();

    Coach findCoachByClubName(String clubName);
}
