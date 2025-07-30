package com.example.golf.tournaments;

import com.example.golf.members.Member;
import com.example.golf.members.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    public Tournament updateTournament(Long id, Tournament updated) {
        return tournamentRepository.findById(id)
                .map(t -> {
                    t.setStartDate(updated.getStartDate());
                    t.setEndDate(updated.getEndDate());
                    t.setLocation(updated.getLocation());
                    t.setEntryFee(updated.getEntryFee());
                    t.setCashPrize(updated.getCashPrize());
                    t.setParticipants(updated.getParticipants());
                    return tournamentRepository.save(t);
                }).orElseThrow(() -> new RuntimeException("Tournament not found"));
    }

    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }

    public Tournament addParticipant(Long tournamentId, Long memberId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        if (!tournament.getParticipants().contains(member)) {
            tournament.getParticipants().add(member);
        }

        return tournamentRepository.save(tournament);
    }

    public List<Tournament> searchByStartDate(LocalDate startDate) {
        return tournamentRepository.findByStartDate(startDate);
    }

    public List<Tournament> searchByLocation(String location) {
        return tournamentRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<Tournament> searchByParticipant(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        return tournamentRepository.findByParticipantsContaining(member);
    }
}
