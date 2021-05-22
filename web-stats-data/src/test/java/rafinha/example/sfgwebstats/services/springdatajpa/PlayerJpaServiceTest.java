package rafinha.example.sfgwebstats.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Player;
import rafinha.example.sfgwebstats.repositories.PlayerRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerJpaServiceTest {

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    PlayerJpaService playerJpaService;

    Player player1;
    Player player2;
    Player player3;

    Club fcBarcelona;
    Set<Player> fcBarcelonaPlayers = new HashSet<>();

    Club realMadryt;
    Set<Player> realMadrytPlayers = new HashSet<>();

    @BeforeEach
    void setUp() {
        fcBarcelona = Club.builder().id(1L).name("FC Barcelona").playerSet(fcBarcelonaPlayers).build();
        realMadryt = Club.builder().id(2L).name("Real Madryt").playerSet(realMadrytPlayers).build();
        player1 = Player.builder().id(1l).firstName("Rafał").lastName("Moczydłowski").club(fcBarcelona).build();
        player2 = Player.builder().id(2L).firstName("Leo").lastName("Messi").club(fcBarcelona).build();
        player3 = Player.builder().id(3L).firstName("Luka").lastName("Modrić").club(realMadryt).build();
        fcBarcelonaPlayers.add(player1);
        fcBarcelonaPlayers.add(player2);
        realMadrytPlayers.add(player3);
    }

    @Test
    void findByLastNamePlayerOne() {
        when(playerRepository.findByLastName(any())).thenReturn(player1);

        Player testPlayerMoczydłowski = playerJpaService.findByLastName("Moczydłowski");

        assertEquals("Moczydłowski", testPlayerMoczydłowski.getLastName());
        assertNotEquals("Messi", testPlayerMoczydłowski.getLastName());
    }

    @Test
    void findByLastNamePlayerTwo() {
        when(playerRepository.findByLastName(any())).thenReturn(player2);

        Player testPlayerMessi = playerJpaService.findByLastName("Messi");

        assertNotEquals("Moczydłowski", testPlayerMessi.getLastName());
        assertEquals("Messi", testPlayerMessi.getLastName());
    }

    @Test
    void findAllByClubFcBarcelona() {
        when(playerRepository.findAllByClub(anyString())).thenReturn(fcBarcelonaPlayers);

        Set<Player> players = playerJpaService.findAllByClub(fcBarcelona);

        assertNotNull(players);
        assertEquals(2, players.size());
    }

    @Test
    void findAllByClubRealMadryt() {
        when(playerRepository.findAllByClub(anyString())).thenReturn(realMadrytPlayers);

        Set<Player> players = playerJpaService.findAllByClub(realMadryt);

        assertNotNull(players);
        assertEquals(1, players.size());
    }

    @Test
    void findAll() {
        when(playerRepository.findAll()).thenReturn(fcBarcelonaPlayers);

        Set<Player> players = playerJpaService.findAll();

        assertNotNull(players);
        assertEquals(2, players.size());
    }

    @Test
    void findById() {
        when(playerRepository.findById(anyLong())).thenReturn(Optional.of(player1));

        Player player = playerJpaService.findById(1L);

        assertNotNull(player);
        assertEquals("Rafał Moczydłowski", player.getFullName());
    }

    @Test
    void findByIdNotFound() {
        when(playerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Player player = playerJpaService.findById(1L);

        assertNull(player);
    }

    @Test
    void save() {
        Player newPlayer = Player.builder().id(3L).build();

        when(playerRepository.save(any())).thenReturn(newPlayer);

        Player savedPlayer = playerJpaService.save(newPlayer);

        assertNotNull(savedPlayer);
        assertEquals(3L, savedPlayer.getId());
    }

    @Test
    void delete() {
        playerJpaService.delete(player2);

        verify(playerRepository).delete(any()); // default is 1 times
    }

    @Test
    void deleteById() {
        playerJpaService.deleteById(2L);

        verify(playerRepository).deleteById(anyLong()); // default is 1 times
    }
}