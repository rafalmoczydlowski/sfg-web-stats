package rafinha.example.sfgwebstats.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.model.Player;
import rafinha.example.sfgwebstats.services.ClubService;
import rafinha.example.sfgwebstats.services.PlayerService;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @Mock
    PlayerService service;

    @Mock
    ClubService clubService;

    @InjectMocks
    PlayerController controller;

    Club club;
    Set<Player> playerSet;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        club = Club.builder().id(1L).build();

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

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/players/clubs/1/player/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("club"))
                .andExpect(model().attributeExists("player"))
                .andExpect(view().name("players/createOrUpdateForm"));
    }

    @Test
    void processCreationForm() throws Exception {
        mockMvc.perform(post("/players/clubs/1/player/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/clubs/1"));

        verify(service).save(ArgumentMatchers.any());
    }

    @Test
    void initUpdateForm() throws Exception {
        when(clubService.findById(anyLong())).thenReturn(club);
        when(service.findById(anyLong())).thenReturn(Player.builder().id(2L).build());

        mockMvc.perform(get("/players/clubs/1/player/2/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("club"))
                .andExpect(model().attributeExists("player"))
                .andExpect(view().name("players/createOrUpdateForm"));
    }

    @Test
    void processUpdateForm() throws Exception {
        mockMvc.perform(post("/players/clubs/1/player/2/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/clubs/1"));
    }
}