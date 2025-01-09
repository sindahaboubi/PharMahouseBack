package PharmaHouseBE.repositories;

import PharmaHouseBE.entities.LigneDeCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface LigneDeCommandeRepository extends JpaRepository<LigneDeCommande, Long> {
    @Query("SELECT l.medicament.titre, SUM(l.quantite) FROM LigneDeCommande l GROUP BY l.medicament.titre ORDER BY SUM(l.quantite) DESC")
    List<Object[]> findMostConsumedMedications();

    @Query("SELECT l.medicament.id AS id, l.medicament.titre AS titre, SUM(l.quantite) AS totalQuantity FROM LigneDeCommande l GROUP BY l.medicament.id ORDER BY totalQuantity DESC")
    List<Map<String, Object>> getTopMedications();
}
