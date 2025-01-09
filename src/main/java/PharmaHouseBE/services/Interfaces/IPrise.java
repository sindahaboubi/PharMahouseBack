package PharmaHouseBE.services.Interfaces;

import PharmaHouseBE.entities.Ordonnance;
import PharmaHouseBE.entities.Prise;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IPrise {
    public Set<Prise> addMedicamentsToOrdonnance(Long ordonnanceId, Set<Prise> ordonnanceMedicaments);
    List<Prise> addPrises(Long userId, List<Prise> prises);
    public List<Prise> getMedicamentsByOrdonnance(Long ordonnanceId);
    public List<Prise> getPrisesByUserId(Long userId);
}
