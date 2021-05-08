package rafinha.example.sfgwebstats.services.springdatajpa;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rafinha.example.sfgwebstats.model.PlayerType;
import rafinha.example.sfgwebstats.repositories.PlayerTypeRepository;
import rafinha.example.sfgwebstats.services.PlayerTypeService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PlayerTypeJpaService implements PlayerTypeService {

    private final PlayerTypeRepository playerTypeRepository;


    public PlayerTypeJpaService(PlayerTypeRepository playerTypeRepository) {
        this.playerTypeRepository = playerTypeRepository;
    }

    @Override
    public Set<PlayerType> findAll() {
        Set<PlayerType> playerTypeSet = new HashSet<>();
        playerTypeRepository.findAll().forEach(playerTypeSet::add);
        return playerTypeSet;
    }

    @Override
    public PlayerType findById(Long aLong) {
        return playerTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public PlayerType save(PlayerType object) {
        return playerTypeRepository.save(object);
    }

    @Override
    public void delete(PlayerType object) {
        playerTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        playerTypeRepository.deleteById(aLong);
    }
}
