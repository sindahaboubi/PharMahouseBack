package PharmaHouseBE.services.Interfaces;

import PharmaHouseBE.entities.Medicament;
import PharmaHouseBE.entities.Ordonnance;
import PharmaHouseBE.entities.Prise;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IOrdonnance {
    public Ordonnance addOrdonnance(Ordonnance o);
    List<Ordonnance> getOrdonnancesByUserId(Long userId);
    public Ordonnance getOrdonnanceById (Long id);
    Ordonnance updateOrdonnance(Long id, Ordonnance updatedOrdonnance);

    public void deleteOrdonnance(Long ordonnanceId);


}