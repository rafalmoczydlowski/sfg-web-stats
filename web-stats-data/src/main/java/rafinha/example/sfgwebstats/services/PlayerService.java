package rafinha.example.sfgwebstats.services;

import rafinha.example.sfgwebstats.model.Player;

import java.util.Set;

public interface PlayerService {

    Player findByLastName(String lastName);

    Player findById(Long id);

    Player save(Player player);

    Set<Player> findAll();

    Set<Player> findAllByClub(String clubName);
}
