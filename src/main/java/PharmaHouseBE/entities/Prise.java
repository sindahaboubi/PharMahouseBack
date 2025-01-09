package PharmaHouseBE.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Prise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int priseJour;
    private int priseMidi;
    private int priseSoir;
    private LocalDate dateDebutPrise;
    private LocalDate dateFinPrise;

    @ManyToOne
    @JoinColumn(name = "ordonnance_id")
    @JsonIgnoreProperties("ordonnanceMedicaments")
    private Ordonnance ordonnance;

    @ManyToOne
    @JoinColumn(name = "medicament_id")
    @JsonIgnoreProperties("ordonnanceMedicaments")
    private Medicament medicament;
}

