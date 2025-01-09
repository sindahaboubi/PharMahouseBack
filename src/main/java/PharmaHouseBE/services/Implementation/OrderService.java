package PharmaHouseBE.services.Implementation;
import PharmaHouseBE.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public int getOrdersCount() {
        return (int) orderRepository.count();
    }

    public float getTotalRevenue() {
        return orderRepository.calculateTotalRevenue();
    }

    public int getUniqueCustomersCount() {
        return orderRepository.findUniqueCustomersCount();
    }
}
