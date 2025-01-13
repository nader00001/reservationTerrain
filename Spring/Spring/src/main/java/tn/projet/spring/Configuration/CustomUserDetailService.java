package tn.projet.spring.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tn.projet.spring.repository.UserRepository;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository utilisateurRepo;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        tn.projet.spring.Models.User utilisateur = utilisateurRepo.findByEmail(username);

        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur not found: " + username);
        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole());
        return new User(utilisateur.getEmail(), utilisateur.getPassword(), Collections.emptyList());
    }

}
