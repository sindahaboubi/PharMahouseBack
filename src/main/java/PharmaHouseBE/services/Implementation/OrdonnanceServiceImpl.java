package PharmaHouseBE.services.Implementation;

import PharmaHouseBE.entities.Ordonnance;
import PharmaHouseBE.entities.Prise;
import PharmaHouseBE.entities.Utilisateur;
import PharmaHouseBE.repositories.MedicamentRepository;
import PharmaHouseBE.repositories.OrdonnanceRepository;
import PharmaHouseBE.repositories.UtilisateurRepository;
import PharmaHouseBE.services.Interfaces.IOrdonnance;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@AllArgsConstructor
@Service
public class OrdonnanceServiceImpl implements IOrdonnance {
    private OrdonnanceRepository ordonnanceRepository;
    private UtilisateurRepository utilisateurRepository;
    private MedicamentRepository medicamentRepository;

    @Override
    public Ordonnance addOrdonnance(Ordonnance ordonnance) {
        return ordonnanceRepository.save(ordonnance);
    }

    @Override
    public List<Ordonnance> getOrdonnancesByUserId(Long userId) {
        Utilisateur user = utilisateurRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found for id: " + userId);
        }
        return ordonnanceRepository.findByUtilisateur(user);
    }

    @Override
    public Ordonnance getOrdonnanceById(Long id) {
        return ordonnanceRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Ordonnance updateOrdonnance(Long id, Ordonnance updatedOrdonnance) {
        Ordonnance existingOrdonnance = ordonnanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordonnance non trouvée avec l'id: " + id));
        existingOrdonnance.setPhoto(updatedOrdonnance.getPhoto());
        existingOrdonnance.setEtat(updatedOrdonnance.getEtat());
        return ordonnanceRepository.save(existingOrdonnance);
    }

    public void deleteOrdonnance(Long ordonnanceId) {
        ordonnanceRepository.deleteById(ordonnanceId);
    }



}
