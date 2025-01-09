package PharmaHouseBE.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Medicament implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private Date dateExpiration;
    private double prix;
    private int quantite;
    private String fabricant;
    private String dosage;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo;


    @ManyToOne
    private Unite unite;

    @OneToMany(mappedBy = "medicament", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("medicament")
    private Set<Prise> ordonnanceMedicaments = new HashSet<>();

}
