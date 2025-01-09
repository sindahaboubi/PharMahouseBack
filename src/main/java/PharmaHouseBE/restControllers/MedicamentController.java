package PharmaHouseBE.restControllers;

import PharmaHouseBE.entities.Medicament;
import PharmaHouseBE.entities.Unite;
import PharmaHouseBE.services.Interfaces.IMedicament;
import PharmaHouseBE.services.Interfaces.IUnite;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/medicaments")
public class MedicamentController {

    @Autowired
    private IMedicament medicamentService;


    @Autowired
    private IUnite uniteService;

    @GetMapping
    public List<Medicament> findAll() {
        return medicamentService.findAll();
    }

    @GetMapping("/{id}")
    public Medicament findById(@PathVariable Long id) {
        return medicamentService.findById(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Medicament> save(
            @RequestParam("titre") String titre,
            @RequestParam("description") String description,
            @RequestParam("dateExpiration") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateExpiration,
            @RequestParam("prix") double prix,
            @RequestParam("quantite") int quantite,
            @RequestParam("fabricant") String fabricant,
            @RequestParam("dosage") String dosage,
            @RequestParam("uniteId") Long uniteId,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            Medicament medicament = new Medicament();
            medicament.setTitre(titre);
            medicament.setDescription(description);
            medicament.setDateExpiration(Date.from(dateExpiration.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            medicament.setPrix(prix);
            medicament.setQuantite(quantite);
            medicament.setFabricant(fabricant);
            medicament.setDosage(dosage);

            if (file != null && !file.isEmpty()) {
                medicament.setPhoto(file.getBytes());
            }

            Unite unite = uniteService.findById(uniteId);
            if (unite == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            medicament.setUnite(unite);
            Medicament savedMedicament = medicamentService.save(medicament);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMedicament);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Medicament> update(
            @PathVariable Long id,
            @RequestParam("titre") String titre,
            @RequestParam("description") String description,
            @RequestParam("dateExpiration") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateExpiration,
            @RequestParam("prix") double prix,
            @RequestParam("quantite") int quantite,
            @RequestParam("fabricant") String fabricant,
            @RequestParam("dosage") String dosage,
            @RequestParam("uniteId") Long uniteId,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            Medicament existingMedicament = medicamentService.findById(id);
            if (existingMedicament == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            existingMedicament.setTitre(titre);
            existingMedicament.setDescription(description);
            existingMedicament.setDateExpiration(Date.from(dateExpiration.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            existingMedicament.setPrix(prix);
            existingMedicament.setQuantite(quantite);
            existingMedicament.setFabricant(fabricant);
            existingMedicament.setDosage(dosage);

            if (file != null && !file.isEmpty()) {
                existingMedicament.setPhoto(file.getBytes());
            }

            Unite unite = uniteService.findById(uniteId);
            if (unite == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            existingMedicament.setUnite(unite);
            Medicament updatedMedicament = medicamentService.save(existingMedicament);
            return ResponseEntity.ok(updatedMedicament);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        medicamentService.deleteById(id);
    }
}
