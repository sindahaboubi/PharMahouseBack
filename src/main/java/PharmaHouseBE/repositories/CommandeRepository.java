package PharmaHouseBE.repositories;

import PharmaHouseBE.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query("SELECT c.dateOperation, COUNT(c) FROM Commande c WHERE YEAR(c.dateOperation) = YEAR(:currentDate) GROUP BY c.dateOperation ORDER BY c.dateOperation")
    List<Object[]> findCommandesByCurrentYear(Date currentDate);

    @Query("SELECT MONTH(c.dateOperation) AS month, COUNT(c) AS count FROM Commande c WHERE YEAR(c.dateOperation) = YEAR(CURRENT_DATE) GROUP BY MONTH(c.dateOperation) ORDER BY month")
    List<Map<String, Object>> getMonthlyCommandes();

    @Query("SELECT c.etatLivraison AS status, COUNT(c) AS count " +
            "FROM Commande c " +
            "GROUP BY c.etatLivraison")
    List<Map<String, Object>> getOrderStatusPieChartData();
}
