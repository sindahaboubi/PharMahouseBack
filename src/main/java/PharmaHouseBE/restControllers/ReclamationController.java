package PharmaHouseBE.restControllers;


import PharmaHouseBE.entities.Reclamation;
import PharmaHouseBE.repositories.ReclamationRepository;
import PharmaHouseBE.services.Implementation.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/reclamations")
@CrossOrigin(origins = "http://localhost:4200") // Adapt this URL to your Angular app's URL
public class ReclamationController {

    @Autowired
    private ReclamationService reclamationService;

    @Autowired
    private ReclamationRepository reclamationRepository;

    @PostMapping
    public Reclamation createReclamation(@RequestBody Reclamation reclamation) {
        return reclamationService.saveReclamation(reclamation);
    }

    @GetMapping
    public List<Reclamation> getAllReclamations() {
        return reclamationService.getAllReclamations();
    }

    @GetMapping("/{id}")
    public Reclamation getReclamationById(@PathVariable Long id) {
        return reclamationService.getReclamationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReclamation(@PathVariable Long id) {
        reclamationService.deleteReclamation(id);
    }

    // Endpoint pour envoyer une réponse à une réclamation
    @PutMapping("/{id}/response")
    public Reclamation respondToReclamation(@PathVariable Long id, @RequestBody Map<String, String> request) {
        Optional<Reclamation> optionalReclamation = reclamationRepository.findById(id);
        if (optionalReclamation.isPresent()) {
            Reclamation reclamation = optionalReclamation.get();
            reclamation.setResponse(request.get("response"));
            return reclamationRepository.save(reclamation);
        } else {
            throw new RuntimeException("Réclamation non trouvée");
        }
    }

}
