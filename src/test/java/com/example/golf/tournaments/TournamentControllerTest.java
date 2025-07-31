package com.example.golf.tournaments;

import com.example.golf.members.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TournamentController.class)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class TournamentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TournamentService tournamentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllTournaments() throws Exception {
        Tournament tournament = new Tournament(LocalDate.now(), LocalDate.now().plusDays(1), "Paris", 100, 1000);
        Mockito.when(tournamentService.getAllTournaments()).thenReturn(List.of(tournament));

        mockMvc.perform(get("/api/tournaments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].location").value("Paris"));
    }

    @Test
    void shouldReturnTournamentById() throws Exception {
        Tournament tournament = new Tournament(LocalDate.now(), LocalDate.now().plusDays(1), "Berlin", 50, 500);
        tournament.setId(1L);
        Mockito.when(tournamentService.getTournamentById(1L)).thenReturn(Optional.of(tournament));

        mockMvc.perform(get("/api/tournaments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("Berlin"));
    }

    @Test
    void shouldCreateTournament() throws Exception {
        Tournament tournament = new Tournament(LocalDate.now(), LocalDate.now().plusDays(1), "London", 70, 700);
        Mockito.when(tournamentService.createTournament(Mockito.any())).thenReturn(tournament);

        mockMvc.perform(post("/api/tournaments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tournament)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("London"));
    }

    @Test
    void shouldUpdateTournament() throws Exception {
        Tournament updated = new Tournament(LocalDate.now(), LocalDate.now().plusDays(3), "Rome", 90, 900);
        Mockito.when(tournamentService.updateTournament(Mockito.eq(1L), Mockito.any())).thenReturn(updated);

        mockMvc.perform(put("/api/tournaments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("Rome"));
    }

    @Test
    void shouldAddParticipant() throws Exception {
        Member member = new Member();
        member.setId(5L);

        Tournament tournament = new Tournament();
        tournament.setId(1L);
        tournament.setParticipants(Set.of(member));

        Mockito.when(tournamentService.addParticipant(1L, 5L)).thenReturn(tournament);

        mockMvc.perform(post("/api/tournaments/1/addParticipant/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.participants[0].id").value(5));
    }

    @Test
    void shouldSearchByStartDate() throws Exception {
        LocalDate date = LocalDate.of(2025, 7, 31);
        Tournament tournament = new Tournament(date, date.plusDays(1), "Tokyo", 60, 600);
        Mockito.when(tournamentService.searchByStartDate(date)).thenReturn(List.of(tournament));

        mockMvc.perform(get("/api/tournaments/search/startDate")
                        .param("date", date.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].location").value("Tokyo"));
    }

    @Test
    void shouldSearchByLocation() throws Exception {
        Tournament tournament = new Tournament(LocalDate.now(), LocalDate.now().plusDays(1), "Madrid", 80, 800);
        Mockito.when(tournamentService.searchByLocation("mad")).thenReturn(List.of(tournament));

        mockMvc.perform(get("/api/tournaments/search/location")
                        .param("location", "mad"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].location").value("Madrid"));
    }

    @Test
    void shouldSearchByParticipant() throws Exception {
        Member member = new Member();
        member.setId(10L);

        Tournament tournament = new Tournament();
        tournament.setParticipants(Set.of(member));

        Mockito.when(tournamentService.searchByParticipant(10L)).thenReturn(List.of(tournament));

        mockMvc.perform(get("/api/tournaments/search/participant")
                        .param("memberId", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].participants[0].id").value(10));
    }
}