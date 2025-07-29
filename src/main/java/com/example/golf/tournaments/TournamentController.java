package tournaments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @PostMapping
    public Tournament add(@RequestBody Tournament tournament) {
        return tournamentService.save(tournament);
    }

    @GetMapping
    public List<Tournament> getAll() {
        return tournamentService.getAll();
    }

    @GetMapping("/{id}")
    public Tournament getById(@PathVariable Long id) {
        return tournamentService.getById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tournamentService.delete(id);
    }
}
