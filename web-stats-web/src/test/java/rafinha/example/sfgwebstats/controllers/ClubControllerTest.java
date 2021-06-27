package rafinha.example.sfgwebstats.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rafinha.example.sfgwebstats.model.Club;
import rafinha.example.sfgwebstats.services.ClubService;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ClubControllerTest {

    @Mock
    ClubService clubService;

    @InjectMocks
    ClubController controller;

    Set<Club> clubSet;

    MockMvc mvc;

    @BeforeEach
    void setUp() {
        clubSet = new HashSet<>();
        clubSet.add(Club.builder().id(1L).build());
        clubSet.add(Club.builder().id(2L).build());

        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listClubs() throws Exception {
        when(clubService.findAll()).thenReturn(clubSet);

        mvc.perform(get("/clubs/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("clubs/index"))
                .andExpect(model().attribute("clubs", hasSize(2)));
    }

    @Test
    void showClub() throws Exception {
        when(clubService.findById(anyLong())).thenReturn(Club.builder().id(4L).build());

        mvc.perform(get("/clubs/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("clubs/clubDetails"))
                .andExpect(model().attribute("club", hasProperty("id", is(4L))));
    }
}