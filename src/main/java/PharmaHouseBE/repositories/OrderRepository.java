package PharmaHouseBE.repositories;

import PharmaHouseBE.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("SELECT SUM(o.Total) FROM Order o")
    float calculateTotalRevenue();

    @Query("SELECT COUNT(DISTINCT o.customerName) FROM Order o")
    int findUniqueCustomersCount();
}
