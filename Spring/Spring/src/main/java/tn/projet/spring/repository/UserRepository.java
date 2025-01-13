package tn.projet.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.projet.spring.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);  // Vérifie si l'email est déjà enregistré
    User findByEmail(String email);       // Trouve un utilisateur par email
}
