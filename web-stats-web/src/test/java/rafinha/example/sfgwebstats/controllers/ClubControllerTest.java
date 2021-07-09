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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoInteractions;
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
    void showClub() throws Exception {
        when(clubService.findById(anyLong())).thenReturn(Club.builder().id(4L).build());

        mvc.perform(get("/clubs/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("clubs/clubDetails"))
                .andExpect(model().attribute("club", hasProperty("id", is(4L))));
    }

    @Test
    void findClubs() throws Exception {
        mvc.perform(get("/clubs/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("clubs/findClubs"))
                .andExpect(model().attributeExists("club"));

        verifyNoInteractions(clubService);
    }

    @Test
    void testFindFormWithManyClubs() throws Exception {
        when(clubService.findAllByNameLike(anyString()))
                .thenReturn(Arrays.asList(Club.builder().id(5L).build(), Club.builder().id(6L).build()));

        mvc.perform(get("/clubs"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("selections", hasSize(2)))
                .andExpect(view().name("clubs/clubsList"));
    }

    @Test
    void testFindFormWithOneClub() throws Exception {
        when(clubService.findAllByNameLike(anyString())).thenReturn(Arrays.asList(Club.builder().id(5L).build()));

        mvc.perform(get("/clubs"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/clubs/5"));
    }

    @Test
    void testFindFormWithEmpty() throws Exception {
        when(clubService.findAllByNameLike(anyString())).thenReturn(Collections.EMPTY_LIST);

        mvc.perform(get("/clubs")
                .param("name", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("clubs/findClubs"));
    }
}