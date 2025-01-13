package tn.projet.spring.Dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AvailabilityRequest {
    private Long terrainId;
    private Long gymnasiumId;  // Ajout de l'attribut gymnasiumId
    private String startDate;
    private String endDate;

    // Getter pour terrainId
    public Long getTerrainId() {
        return terrainId;
    }

    // Setter pour terrainId
    public void setTerrainId(Long terrainId) {
        this.terrainId = terrainId;
    }

    // Conversion du string startDate en LocalDateTime
    public LocalDateTime getStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(startDate, formatter);
    }

    // Setter pour startDate
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    // Conversion du string endDate en LocalDateTime
    public LocalDateTime getEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(endDate, formatter);
    }

    // Setter pour endDate
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    // Getter pour gymnasiumId
    public Long getGymnasiumId() {
        return gymnasiumId;
    }

    // Setter pour gymnasiumId
    public void setGymnasiumId(Long gymnasiumId) {
        this.gymnasiumId = gymnasiumId;
    }
}
