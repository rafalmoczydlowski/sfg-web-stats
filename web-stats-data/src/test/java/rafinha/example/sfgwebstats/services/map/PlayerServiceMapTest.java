package rafinha.example.sfgwebstats.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rafinha.example.sfgwebstats.model.Player;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PlayerServiceMapTest {

    PlayerServiceMap playerServiceMap;

    Set<Player> playerSet = new HashSet<>();

    final Long playerId = 1L;
    final String firstName = "Rafał";
    final String lastName = "Kowalski";

    @BeforeEach
    void setPlayerServiceMap(){
        playerServiceMap = new PlayerServiceMap(new PlayerTypeMapService()); // DI

        Player player1 = Player.builder().id(playerId).lastName(lastName).firstName(firstName).build();

        playerServiceMap.save(player1);
    }
    @Test
    void findByLastName() {
        Player kowalski = playerServiceMap.findByLastName(lastName);

        assertNotNull(kowalski);
        assertEquals(playerId, kowalski.getId());
        assertEquals("Rafał Kowalski", kowalski.getFullName());
    }

    @Test
    void findByLastNameNotFound() {
        Player noname = playerServiceMap.findByLastName("noname");

        assertNull(noname);
    }

    @Test
    void findAll() {
        playerSet = playerServiceMap.findAll();
        assertEquals(1, playerSet.size());
    }

    @Test
    void findById() {
        Player player = playerServiceMap.findById(playerId);

        assertEquals(playerId, player.getId());
    }

    @Test
    void delete() {
        playerServiceMap.delete(playerServiceMap.findById(playerId));

        assertEquals(0, playerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        playerServiceMap.deleteById(playerId);

        assertEquals(0, playerServiceMap.findAll().size());
    }

    @Test
    void saveId() {
        Long expected = 2L;

        Player savedPlayer = playerServiceMap.save(Player.builder().id(expected).build());

        assertEquals(expected, savedPlayer.getId());
        assertEquals(2, playerServiceMap.findAll().size());
    }

    @Test
    void saveNoId() {
        Player savedPlayer = playerServiceMap.save(Player.builder().build());

        assertNotNull(savedPlayer);
        assertNotNull(savedPlayer.getId());
    }
}