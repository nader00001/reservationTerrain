package tn.projet.spring.Dto;

import lombok.Builder;
import lombok.Data;
import tn.projet.spring.Models.User;

@Builder
@Data
public class LoginResponse {
    private String token;
    private User utilisateur;
}
