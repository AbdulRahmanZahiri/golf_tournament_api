package tournaments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    public Tournament save(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public List<Tournament> getAll() {
        return tournamentRepository.findAll();
    }

    public Optional<Tournament> getById(Long id) {
        return tournamentRepository.findById(id);
    }

    public void delete(Long id) {
        tournamentRepository.deleteById(id);
    }
}
