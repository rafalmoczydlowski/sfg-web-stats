package rafinha.example.sfgwebstats.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rafinha.example.sfgwebstats.model.Player;
import rafinha.example.sfgwebstats.services.PlayerService;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @Mock
    PlayerService service;

    @InjectMocks
    PlayerController controller;

    Set<Player> playerSet;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        playerSet = new HashSet<>();
        playerSet.add(Player.builder().id(1L).build());
        playerSet.add(Player.builder().id(2L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void listPlayers() throws Exception {
        when(service.findAll()).thenReturn(playerSet);

        mockMvc.perform(get("/players/index"))
                .andExpect(view().name("players/index"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("players", hasSize(2)));
    }
}