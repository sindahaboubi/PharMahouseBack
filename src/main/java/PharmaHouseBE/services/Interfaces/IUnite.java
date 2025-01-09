package PharmaHouseBE.services.Interfaces;

import PharmaHouseBE.entities.Unite;

import java.util.List;

public interface IUnite {
    List<Unite> findAll();
    Unite findById(Long id);
    Unite save(Unite Unite);
    void deleteById(Long id);
}
