package com.example.golf.tournaments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/{id}")
    public Optional<Tournament> getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id);
    }

    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentService.createTournament(tournament);
    }

    @PutMapping("/{id}")
    public Tournament updateTournament(@PathVariable Long id, @RequestBody Tournament updated) {
        return tournamentService.updateTournament(id, updated);
    }

    @PostMapping("/{tournamentId}/addParticipant/{memberId}")
    public Tournament addParticipant(@PathVariable Long tournamentId, @PathVariable Long memberId) {
        return tournamentService.addParticipant(tournamentId, memberId);
    }

    @DeleteMapping("/{id}")
    public void deleteTournament(@PathVariable Long id) {
        tournamentService.deleteTournament(id);
    }

    @GetMapping("/search/startDate")
    public List<Tournament> searchByStartDate(@RequestParam String date) {
        return tournamentService.searchByStartDate(LocalDate.parse(date));
    }
    @GetMapping("/search/location")
    public List<Tournament> searchByLocation(@RequestParam String location) {
        return tournamentService.searchByLocation(location);
    }
    @GetMapping("/search/participant")
    public List<Tournament> searchByParticipant(@RequestParam Long memberId) {
        return tournamentService.searchByParticipant(memberId);
    }

}
