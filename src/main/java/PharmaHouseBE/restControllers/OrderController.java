package PharmaHouseBE.restControllers;

import PharmaHouseBE.entities.Order;
import PharmaHouseBE.entities.OrderStatistics;
import PharmaHouseBE.repositories.OrderRepository;
import PharmaHouseBE.services.Implementation.OrderService;
import PharmaHouseBE.services.Implementation.UpdateStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody UpdateStatusRequest request) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(request.getStatus());
            orderRepository.save(order);
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/deliveries")
    public ResponseEntity<List<Order>> getDeliveries() {
        List<Order> deliveries = orderRepository.findAll();
        return ResponseEntity.ok(deliveries);
    }


    @Autowired
    private OrderService orderService;

    @GetMapping("/statistics")
    public OrderStatistics getOrderStatistics() {
        OrderStatistics stats = new OrderStatistics();
        stats.setOrdersCount(orderService.getOrdersCount());
        stats.setTotalRevenue(orderService.getTotalRevenue());
        stats.setUniqueCustomers(orderService.getUniqueCustomersCount());
        return stats;
    }
}