package PharmaHouseBE.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class LigneDeCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference // Use this to handle serialization

    private Commande commande;

    @ManyToOne(cascade = CascadeType.ALL) // Cascade the delete operation to LigneDeCommande

    private Medicament medicament;

    private int quantite;
}
