package PharmaHouseBE.restControllers;

import PharmaHouseBE.entities.Prise;
import PharmaHouseBE.services.Interfaces.IPrise;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/prises")
public class PriseController {
    private IPrise iPrise;

    @PostMapping("/{ordonnanceId}/medicaments")
    public Set<Prise> addMedicamentsToOrdonnance(@PathVariable Long ordonnanceId,
                                                 @RequestBody Set<Prise> ordonnanceMedicaments) {
        return iPrise.addMedicamentsToOrdonnance(ordonnanceId, ordonnanceMedicaments);
    }

    @PostMapping("/CreatePrises/{userId}")
    public List<Prise> addPrises(@PathVariable Long userId, @RequestBody List<Prise> prises) {
        return iPrise.addPrises(userId, prises);
    }

    @GetMapping("/user/{userId}")
    public List<Prise> getPrisesByUserId(@PathVariable Long userId) {
        return iPrise.getPrisesByUserId(userId);
    }

}
