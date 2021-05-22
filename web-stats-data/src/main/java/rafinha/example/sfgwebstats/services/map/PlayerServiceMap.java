package rafinha.example.sfgwebstats.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Player;
import rafinha.example.sfgwebstats.model.PlayerType;
import rafinha.example.sfgwebstats.services.PlayerService;
import rafinha.example.sfgwebstats.services.PlayerTypeService;

import java.util.Collections;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class PlayerServiceMap extends AbstractMapService<Player, Long> implements PlayerService {

    private final PlayerTypeService playerTypeService;

    public PlayerServiceMap(PlayerTypeService playerTypeService) {
        this.playerTypeService = playerTypeService;
    }

    @Override
    public Player findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(player -> player.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Player> findAllByClub(Club club) {
        return Collections.emptySet();
    }

    @Override
    public Set<Player> findAll() {
        return super.findAll();
    }

    @Override
    public Player findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void delete(Player object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Player save(Player object) {
        if(object != null && object.getPlayerTypeSet() != null) {
            object.getPlayerTypeSet().forEach(this::accept);
        }
        return super.save(object);
    }

    private void accept(PlayerType playerType) {
        if (playerType.getId() == null) {
            PlayerType savedPlayerType = playerTypeService.save(playerType);
            playerType.setId(savedPlayerType.getId());
        }
    }
}
