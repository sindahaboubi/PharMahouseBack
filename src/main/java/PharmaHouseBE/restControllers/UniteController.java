package PharmaHouseBE.restControllers;



import PharmaHouseBE.entities.Unite;
import PharmaHouseBE.services.Interfaces.IUnite;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/unites")
public class UniteController {


    private IUnite uniteService;

    @GetMapping
    public List<Unite> findAll() {
        return uniteService.findAll();
    }

    @GetMapping("/{id}")
    public Unite findById(@PathVariable Long id) {
        return uniteService.findById(id);
    }

    @PostMapping
    public Unite save(@RequestBody Unite unite) {
        return uniteService.save(unite);
    }

    @PutMapping("/{id}")
    public Unite update(@PathVariable Long id, @RequestBody Unite unite) {
        Unite existingUnite = uniteService.findById(id);
        if (existingUnite != null) {
            unite.setId(id);
            return uniteService.save(unite);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        uniteService.deleteById(id);
    }
}
