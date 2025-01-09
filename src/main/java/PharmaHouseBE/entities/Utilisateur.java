package PharmaHouseBE.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Utilisateur implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Id
    private Long id;

    private String adresse;



    @OneToMany(mappedBy = "utilisateur")
    private Set<Ordonnance> ordonnances;
}
