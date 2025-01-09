package PharmaHouseBE.services.Interfaces;

import PharmaHouseBE.entities.Utilisateur;

import java.util.Optional;

public interface IUtilisateur {
    Optional<Utilisateur> getActuelUtilisateur(Long id);
}
