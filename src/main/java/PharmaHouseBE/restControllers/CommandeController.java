package PharmaHouseBE.restControllers;

import PharmaHouseBE.configuration.CommandeRequest;
import PharmaHouseBE.entities.Commande;
import PharmaHouseBE.entities.LigneDeCommande;
import PharmaHouseBE.entities.Medicament;
import PharmaHouseBE.services.Interfaces.ICommande;
import PharmaHouseBE.services.Interfaces.ILigneDeCommande;
import PharmaHouseBE.services.Interfaces.IMedicament;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/commandes")
public class CommandeController {

    @Autowired
    private ICommande commandeService;

    @Autowired
    private ILigneDeCommande ligneDeCommandeService;

    @Autowired
    private IMedicament medicamentService;



    @PostMapping("/create")
    public Commande createCommande(@RequestBody CommandeRequest commandeRequest) {
        // Create a new Commande
        Commande newCommande = new Commande();
        newCommande.setDateOperation(new Date());
        newCommande.setEtatLivraison("Pending"); // or other initial state
        newCommande.setUtilisateur(commandeRequest.getUtilisateur());
        newCommande.setShippingAddress(commandeRequest.getShippingAddress());

        // Save the Commande
        Commande savedCommande = commandeService.save(newCommande);

        // Create and save LigneDeCommandes
        List<LigneDeCommande> ligneDeCommandes = new ArrayList<>();
        for (LigneDeCommande ligne : commandeRequest.getLigneDeCommandes()) {
            // Fetch the Medicament entity from the database to ensure it is managed
            Medicament managedMedicament = medicamentService.findById(ligne.getMedicament().getId());

            // Decrease the quantity of the medicament
            int updatedQuantity = managedMedicament.getQuantite() - ligne.getQuantite();
            if (updatedQuantity < 0) {
                throw new IllegalArgumentException("Not enough quantity for medicament: " + managedMedicament.getTitre());
            }
            managedMedicament.setQuantite(updatedQuantity);

            // Save the updated medicament
            medicamentService.save(managedMedicament);

            // Set the managed medicament and the saved commande in the ligne de commande
            ligne.setMedicament(managedMedicament);
            ligne.setCommande(savedCommande);

            // Save the ligne de commande
            LigneDeCommande savedLigneDeCommande = ligneDeCommandeService.save(ligne);
            ligneDeCommandes.add(savedLigneDeCommande); // Add saved ligne to list
        }

        savedCommande.setLigneDeCommandes(ligneDeCommandes);

        return savedCommande;
    }


}
