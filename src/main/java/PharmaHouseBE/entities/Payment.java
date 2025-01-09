package PharmaHouseBE.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private boolean paymentStatus;
    private String checksum;
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;
}
