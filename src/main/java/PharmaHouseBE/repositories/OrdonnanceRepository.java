package PharmaHouseBE.repositories;

import PharmaHouseBE.entities.Ordonnance;
import PharmaHouseBE.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdonnanceRepository extends JpaRepository<Ordonnance, Long> {
    List<Ordonnance> findByUtilisateur(Utilisateur utilisateur);
}
