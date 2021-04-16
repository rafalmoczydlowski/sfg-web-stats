package rafinha.example.sfgwebstats.services;

import rafinha.example.sfgwebstats.model.Coach;

public interface CoachService extends CrudService<Coach, Long> {

    Coach findByLastName(String lastName);

    Coach findCoachByClubName(String clubName);
}
