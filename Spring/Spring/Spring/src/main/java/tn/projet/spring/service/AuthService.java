package tn.projet.spring.service;


import org.springframework.http.ResponseEntity;
import tn.projet.spring.Dto.SignupRequest;
import tn.projet.spring.Models.User;

public interface AuthService {

    User createUser(ResponseEntity<?> signupRequest);

    User createUser(SignupRequest signupRequest);
}
