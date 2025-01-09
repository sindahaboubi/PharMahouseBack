package PharmaHouseBE.services.Implementation;

import PharmaHouseBE.entities.Medicament;
import PharmaHouseBE.repositories.MedicamentRepository;
import PharmaHouseBE.services.Interfaces.IMedicament;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class MedicamentServiceImpl implements IMedicament {


    private MedicamentRepository medicamentRepository;

    @Override
    public List<Medicament> findAll() {
        return medicamentRepository.findAll();
    }

    @Override
    public Medicament findById(Long id) {
        return medicamentRepository.findById(id).orElse(null);
    }

    @Override
    public Medicament save(Medicament medicament) {
        return medicamentRepository.save(medicament);
    }

    @Override
    public void deleteById(Long id) {
        medicamentRepository.deleteById(id);
    }
}
