package PharmaHouseBE.repositories;

import PharmaHouseBE.entities.Medicament;
import PharmaHouseBE.entities.Unite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
}

