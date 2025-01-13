package tn.projet.spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Terrain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Nom du terrain
    private String location; // Localisation
    private int capacity; // Capacité maximale du terrain
    private String type; // Type : Football, Basketball, etc.

    private int terrain_count; // Nombre actuel de personnes sur le terrain
    private int soil_moisture; // Humidité du sol en pourcentage
}
