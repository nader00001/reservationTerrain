package tn.projet.spring.Dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import tn.projet.spring.Models.Role; // Assurez-vous que Role est bien importé

@Getter
@Setter
public class SignupRequest {
    private String email;
    private String nom;
    private String prenom;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
