package PharmaHouseBE.services.Interfaces;

import PharmaHouseBE.entities.Commande;

import java.util.List;

public interface ICommande {
    List<Commande> findAll();
    Commande findById(Long id);
    Commande save(Commande commande);
    void deleteById(Long id);
}
