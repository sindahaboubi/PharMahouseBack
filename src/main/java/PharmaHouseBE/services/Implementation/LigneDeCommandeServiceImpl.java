package PharmaHouseBE.services.Implementation;

import PharmaHouseBE.entities.LigneDeCommande;
import PharmaHouseBE.repositories.LigneDeCommandeRepository;
import PharmaHouseBE.services.Interfaces.ILigneDeCommande;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LigneDeCommandeServiceImpl implements ILigneDeCommande {


    private LigneDeCommandeRepository ligneDeCommandeRepository;

    @Override
    public List<LigneDeCommande> findAll() {
        return ligneDeCommandeRepository.findAll();
    }

    @Override
    public LigneDeCommande findById(Long id) {
        return ligneDeCommandeRepository.findById(id).orElse(null);
    }

    @Override
    public LigneDeCommande save(LigneDeCommande ligneDeCommande) {
        return ligneDeCommandeRepository.save(ligneDeCommande);
    }

    @Override
    public void deleteById(Long id) {
        ligneDeCommandeRepository.deleteById(id);
    }
}
