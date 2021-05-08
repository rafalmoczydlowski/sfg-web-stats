package rafinha.example.sfgwebstats.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rafinha.example.sfgwebstats.model.Player;
import rafinha.example.sfgwebstats.repositories.PlayerRepository;
import rafinha.example.sfgwebstats.services.PlayerService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PlayerJpaService implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerJpaService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player findByLastName(String lastName) {
        return playerRepository.findByLastName(lastName);
    }

    @Override
    public Set<Player> findAllByClub(String clubName) {
        return playerRepository.findAllByClub(clubName);
    }

    @Override
    public Set<Player> findAll() {
        Set<Player> players = new HashSet<>();
        playerRepository.findAll().forEach(players::add);
        return players;
    }

    @Override
    public Player findById(Long aLong) {
        return playerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Player save(Player object) {
        return playerRepository.save(object);
    }

    @Override
    public void delete(Player object) {
        playerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        playerRepository.deleteById(aLong);
    }
}
