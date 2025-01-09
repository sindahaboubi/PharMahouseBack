package PharmaHouseBE.services.Implementation;

import PharmaHouseBE.entities.Utilisateur;
import PharmaHouseBE.repositories.UtilisateurRepository;
import PharmaHouseBE.services.Interfaces.IUtilisateur;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UtilisateurServiceImpl implements IUtilisateur {
    private UtilisateurRepository utilisateurRepository;
    @Override
    public Optional<Utilisateur> getActuelUtilisateur(Long id) {
        return utilisateurRepository.findById(id);
    }
}
