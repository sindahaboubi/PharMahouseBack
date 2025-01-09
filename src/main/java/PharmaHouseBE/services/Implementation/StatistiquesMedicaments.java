package PharmaHouseBE.services.Implementation;


import PharmaHouseBE.repositories.CommandeRepository;
import PharmaHouseBE.repositories.LigneDeCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StatistiquesMedicaments {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private LigneDeCommandeRepository ligneDeCommandeRepository;

    public List<Object[]> getMostConsumedMedications() {
        return ligneDeCommandeRepository.findMostConsumedMedications();
    }

    public List<Object[]> findCommandesByCurrentYear(Date currentDate) {
        return commandeRepository.findCommandesByCurrentYear(currentDate);
    }
    public List<Map<String, Object>> getMonthlyCommandes() {
        return commandeRepository.getMonthlyCommandes();
    }

    public List<Map<String, Object>> getTopMedications() {
        return ligneDeCommandeRepository.getTopMedications();
    }

    public List<Map<String, Object>> getPieChartData() {
        return commandeRepository.getOrderStatusPieChartData();
    }
}
