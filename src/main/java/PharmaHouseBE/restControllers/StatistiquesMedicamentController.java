package PharmaHouseBE.restControllers;

import PharmaHouseBE.services.Implementation.StatistiquesMedicaments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatistiquesMedicamentController {

    @Autowired
    private StatistiquesMedicaments statisticsService;



    @GetMapping("/most-consumed-medications")
    public List<Object[]> getMostConsumedMedications() {
        return statisticsService.getMostConsumedMedications();
    }

    @GetMapping("/commandes-by-current-year")
    public List<Object[]> getCommandesByCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        return statisticsService.findCommandesByCurrentYear(currentDate);
    }
    @GetMapping("/monthly-commandes")
    public List<Map<String, Object>> getMonthlyCommandes() {
        return statisticsService.getMonthlyCommandes();
    }

    @GetMapping("/top-medications")
    public List<Map<String, Object>> getTopMedications() {
        return statisticsService.getTopMedications();
    }

    @GetMapping("/pie-chart-data")
    public List<Map<String, Object>> getPieChartData() {
        return statisticsService.getPieChartData();
    }
}
