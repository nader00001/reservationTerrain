package tn.projet.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "terrain_id")
    private Terrain terrain; // Relation avec le terrain

    private String reservedBy; // Le nom ou identifiant de la personne qui réserve

    private LocalDateTime startDate; // Date et heure de début
    private LocalDateTime endDate;   // Date et heure de fin
}
