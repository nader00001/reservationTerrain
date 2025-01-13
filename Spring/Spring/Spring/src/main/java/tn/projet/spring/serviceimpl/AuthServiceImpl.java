package tn.projet.spring.serviceimpl;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.projet.spring.Dto.SignupRequest;
import tn.projet.spring.Models.User;
import tn.projet.spring.repository.UserRepository;
import tn.projet.spring.service.AuthService;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(ResponseEntity<?> signupRequest) {
        return null;
    }

    @Override
    public User createUser(SignupRequest signupRequest) {
        try {
            if (userRepository.existsByEmail(signupRequest.getEmail())) {
                throw new IllegalArgumentException("Cet email est déjà utilisé.");
            }

            User user = new User();
            BeanUtils.copyProperties(signupRequest, user);

            String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
            user.setPassword(hashPassword);

            return userRepository.save(user);

        } catch (Exception e) {
            // Logguer l'exception pour en savoir plus
            e.printStackTrace();
            throw e;  // Vous pouvez également relancer l'exception ici
        }
    }

}
