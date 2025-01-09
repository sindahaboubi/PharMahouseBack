package PharmaHouseBE.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateOperation;
    private String etatLivraison;
    private String shippingAddress;

    @ManyToOne
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL) // Cascade the delete operation to LigneDeCommande
    @JsonManagedReference
    private List<LigneDeCommande> ligneDeCommandes;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<Payment> payments;
}