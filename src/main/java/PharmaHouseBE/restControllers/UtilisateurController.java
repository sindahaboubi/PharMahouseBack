package PharmaHouseBE.restControllers;

import PharmaHouseBE.entities.Utilisateur;
import PharmaHouseBE.services.Interfaces.IUtilisateur;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {
    private IUtilisateur iUtilisateur;

    @GetMapping(value = "/utilisateurActuel/{idutil}")
    public Optional<Utilisateur> getUtilisateur(@PathVariable("idutil") Long id){
        return this.iUtilisateur.getActuelUtilisateur(id);
    }
}
