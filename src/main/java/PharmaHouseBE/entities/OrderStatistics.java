package PharmaHouseBE.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Table(name = "orders_stat") // Renommer la table en "orders"
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderStatistics {
    // Getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int ordersCount;
    private float totalRevenue;
    private int uniqueCustomers;

    public void setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
    }

    public void setTotalRevenue(float totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public void setUniqueCustomers(int uniqueCustomers) {
        this.uniqueCustomers = uniqueCustomers;
    }
}
