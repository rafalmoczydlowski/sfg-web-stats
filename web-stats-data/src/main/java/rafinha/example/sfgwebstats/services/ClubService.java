package rafinha.example.sfgwebstats.services;

import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Coach;

public interface ClubService extends CrudService<Club, Long> {

    Club findClubByCoach(Coach coach);
}
