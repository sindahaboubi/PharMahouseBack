package PharmaHouseBE.repositories;

import PharmaHouseBE.entities.Prise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PriseRepository extends JpaRepository<Prise, Long> {
    List<Prise> findByOrdonnanceId(Long ordonnanceId);

    @Query("SELECT p FROM Prise p WHERE p.ordonnance.utilisateur.id = :userId")
    List<Prise> findAllByUserId(@Param("userId") Long userId);

}
