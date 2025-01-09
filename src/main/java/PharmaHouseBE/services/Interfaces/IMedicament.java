package PharmaHouseBE.services.Interfaces;

import PharmaHouseBE.entities.Medicament;

import java.util.List;

public interface IMedicament {
    List<Medicament> findAll();
    Medicament findById(Long id);
    Medicament save(Medicament medicament);
    void deleteById(Long id);
}
