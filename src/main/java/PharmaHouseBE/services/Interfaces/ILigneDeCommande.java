package PharmaHouseBE.services.Interfaces;

import PharmaHouseBE.entities.LigneDeCommande;

import java.util.List;

public interface ILigneDeCommande {
    List<LigneDeCommande> findAll();
    LigneDeCommande findById(Long id);
    LigneDeCommande save(LigneDeCommande LigneDeCommande);
    void deleteById(Long id);
}
