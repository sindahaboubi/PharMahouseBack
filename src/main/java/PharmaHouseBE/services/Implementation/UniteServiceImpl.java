package PharmaHouseBE.services.Implementation;

import PharmaHouseBE.entities.Unite;
import PharmaHouseBE.repositories.UniteRepository;
import PharmaHouseBE.services.Interfaces.IUnite;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UniteServiceImpl implements IUnite {


    private UniteRepository uniteRepository;

    @Override
    public List<Unite> findAll() {
        return uniteRepository.findAll();
    }

    @Override
    public Unite findById(Long id) {
        return uniteRepository.findById(id).orElse(null);
    }

    @Override
    public Unite save(Unite unite) {
        return uniteRepository.save(unite);
    }

    @Override
    public void deleteById(Long id) {
        uniteRepository.deleteById(id);
    }
}
