package com.example.golf.tournaments;

import com.example.golf.members.Member;
import com.example.golf.members.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TournamentServiceTest {

    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private TournamentService tournamentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateTournament() {
        Tournament tournament = new Tournament(
                LocalDate.now(), LocalDate.now().plusDays(1), "NYC", 100.0, 500.0);

        when(tournamentRepository.save(tournament)).thenReturn(tournament);

        Tournament saved = tournamentService.createTournament(tournament);

        assertEquals("NYC", saved.getLocation());
        verify(tournamentRepository, times(1)).save(tournament);
    }

    @Test
    void shouldReturnAllTournaments() {
        List<Tournament> list = List.of(new Tournament(), new Tournament());
        when(tournamentRepository.findAll()).thenReturn(list);

        List<Tournament> result = tournamentService.getAllTournaments();
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnTournamentById() {
        Tournament t = new Tournament();
        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(t));

        Optional<Tournament> result = tournamentService.getTournamentById(1L);
        assertTrue(result.isPresent());
    }

    @Test
    void shouldUpdateTournament() {
        Tournament existing = new Tournament(
                LocalDate.now(), LocalDate.now().plusDays(1), "Old", 50.0, 200.0);
        Tournament updated = new Tournament(
                LocalDate.now(), LocalDate.now().plusDays(1), "New", 70.0, 250.0);

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(tournamentRepository.save(any())).thenReturn(existing);

        Tournament result = tournamentService.updateTournament(1L, updated);
        assertEquals("New", result.getLocation());
    }

    @Test
    void shouldDeleteTournament() {
        tournamentService.deleteTournament(1L);
        verify(tournamentRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldAddParticipant() {
        Tournament tournament = new Tournament();
        Member member = new Member();
        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        when(memberRepository.findById(2L)).thenReturn(Optional.of(member));
        when(tournamentRepository.save(any())).thenReturn(tournament);

        Tournament result = tournamentService.addParticipant(1L, 2L);
        assertTrue(result.getParticipants().contains(member));
    }

    @Test
    void shouldSearchByStartDate() {
        LocalDate date = LocalDate.of(2025, 1, 1);
        when(tournamentRepository.findByStartDate(date)).thenReturn(List.of(new Tournament()));

        List<Tournament> result = tournamentService.searchByStartDate(date);
        assertEquals(1, result.size());
    }

    @Test
    void shouldSearchByLocation() {
        when(tournamentRepository.findByLocationContainingIgnoreCase("Toronto"))
                .thenReturn(List.of(new Tournament()));

        List<Tournament> result = tournamentService.searchByLocation("Toronto");
        assertEquals(1, result.size());
    }

    @Test
    void shouldSearchByParticipant() {
        Member m = new Member();
        when(memberRepository.findById(99L)).thenReturn(Optional.of(m));
        when(tournamentRepository.findByParticipantsContaining(m)).thenReturn(List.of(new Tournament()));

        List<Tournament> result = tournamentService.searchByParticipant(99L);
        assertEquals(1, result.size());
    }
}
