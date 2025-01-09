package PharmaHouseBE.restControllers;

import PharmaHouseBE.entities.LigneDeCommande;
import PharmaHouseBE.services.Interfaces.ILigneDeCommande;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/ligneDeCommandes")
public class LigneDeCommandeController {


    private ILigneDeCommande ligneDeCommandeService;

    @GetMapping
    public List<LigneDeCommande> findAll() {
        return ligneDeCommandeService.findAll();
    }

    @GetMapping("/{id}")
    public LigneDeCommande findById(@PathVariable Long id) {
        return ligneDeCommandeService.findById(id);
    }

    @PostMapping
    public LigneDeCommande save(@RequestBody LigneDeCommande ligneDeCommande) {
        return ligneDeCommandeService.save(ligneDeCommande);
    }

    @PutMapping("/{id}")
    public LigneDeCommande update(@PathVariable Long id, @RequestBody LigneDeCommande ligneDeCommande) {
        LigneDeCommande existingLigneDeCommande = ligneDeCommandeService.findById(id);
        if (existingLigneDeCommande != null) {
            ligneDeCommande.setId(id);
            return ligneDeCommandeService.save(ligneDeCommande);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        ligneDeCommandeService.deleteById(id);
    }
}
