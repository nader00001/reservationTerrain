package tn.projet.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.projet.spring.entity.Gymnasium;
import tn.projet.spring.entity.Terrain;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // Terrain peut être optionnel dans la réservation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terrain_id")
    @JsonIgnore  // Ignorer le terrain pendant la sérialisation

    private Terrain terrain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gymnasium_id")
    private Gymnasium gymnasium; // Peut être null

    // Ajouter d'autres attributs si nécessaire



    @AssertTrue(message = "La date de début doit être avant la date de fin.")
    public boolean isStartBeforeEnd() {
        return startDate != null && endDate != null && startDate.isBefore(endDate);
    }
}
