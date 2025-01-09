package PharmaHouseBE.configuration;

import PharmaHouseBE.entities.LigneDeCommande;
import PharmaHouseBE.entities.Utilisateur;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommandeRequest {
    private Utilisateur utilisateur;
    private String shippingAddress;
    private List<LigneDeCommande> ligneDeCommandes;
}
