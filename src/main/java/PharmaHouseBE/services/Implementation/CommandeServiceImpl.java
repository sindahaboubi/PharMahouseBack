package PharmaHouseBE.services.Implementation;

import PharmaHouseBE.entities.Commande;
import PharmaHouseBE.repositories.CommandeRepository;
import PharmaHouseBE.services.Interfaces.ICommande;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommandeServiceImpl implements ICommande {


    private CommandeRepository commandeRepository;

    @Override
    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande findById(Long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    @Override
    public Commande save(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public void deleteById(Long id) {
        commandeRepository.deleteById(id);
    }
}
