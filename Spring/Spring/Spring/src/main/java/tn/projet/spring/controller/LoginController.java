package tn.projet.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.projet.spring.Configuration.CustomUserDetailService;
import tn.projet.spring.Dto.LoginRequest;
import tn.projet.spring.Dto.LoginResponse;
import tn.projet.spring.Models.User;
import tn.projet.spring.Utils.JwtUtils;
import tn.projet.spring.repository.UserRepository;


@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserRepository utilisateurRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private JwtUtils jwtUtil;




    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final User user = utilisateurRepo.findByEmail(request.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(LoginResponse.builder().token(jwt).utilisateur(user).build());
    }
}
