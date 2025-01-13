package tn.projet.spring.Dto;

public class AvailabilityResponse {
    private boolean isAvailable;

    public AvailabilityResponse(boolean isAvailable, String s) {
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
