package PharmaHouseBE.restControllers;

import PharmaHouseBE.entities.Ordonnance;
import PharmaHouseBE.entities.Prise;
import PharmaHouseBE.entities.Utilisateur;
import PharmaHouseBE.services.Interfaces.IMedicament;
import PharmaHouseBE.services.Interfaces.IOrdonnance;
import PharmaHouseBE.services.Interfaces.IPrise;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/ordonnances")
public class OrdonnanceController {
    private IOrdonnance iOrdonnance;
    private IMedicament iMedicament;
    private IPrise iPrise;
    @PostMapping("/add")
    public ResponseEntity<Ordonnance> addOrdonnance(
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("userId") Long userId
    ) {
        Utilisateur user = new Utilisateur(userId, null, null);
        try {
            Ordonnance ordonnance = new Ordonnance();
            ordonnance.setEtat(1);
            ordonnance.setDateCreation(LocalDate.now());
            ordonnance.setPhoto(photo.getBytes());
            ordonnance.setUtilisateur(user);
            Ordonnance savedOrdonnance = iOrdonnance.addOrdonnance(ordonnance);
            return new ResponseEntity<>(savedOrdonnance, HttpStatus.CREATED);
        } catch (IOException | EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public List<Ordonnance> getOrdonnancesByUserId(@PathVariable Long userId) {
        return iOrdonnance.getOrdonnancesByUserId(userId);
    }

    @GetMapping("/{id}")
    public Ordonnance getOrdonnanceById(@PathVariable Long id){
        return iOrdonnance.getOrdonnanceById(id);
    }

    @GetMapping("/{ordonnanceId}/medicaments")
    public List<Prise> getMedicamentsByOrdonnance(@PathVariable Long ordonnanceId) {
        return iPrise.getMedicamentsByOrdonnance(ordonnanceId);
    }

    @PutMapping("/{id}/updateEtat")
    public ResponseEntity<Ordonnance> updateOrdonnance(@PathVariable Long id, @RequestBody Ordonnance updatedOrdonnance) {
        Ordonnance updated = iOrdonnance.updateOrdonnance(id, updatedOrdonnance);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{ordonnanceId}")
    public ResponseEntity<String> deleteOrdonnance(@PathVariable Long ordonnanceId) {
        iOrdonnance.deleteOrdonnance(ordonnanceId);
        return new ResponseEntity<>("Ordonnance deleted successfully", HttpStatus.OK);
    }
}
