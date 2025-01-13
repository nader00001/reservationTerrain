package tn.projet.spring.serviceimpl;


import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.projet.spring.Models.User;
import tn.projet.spring.repository.UserRepository;
import tn.projet.spring.service.UserService;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceIm implements UserService {


    UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> retrieveAllUser() {
        return userRepository.findAll();
    }

    @Override
    public org.springframework.security.core.userdetails.User addUser(org.springframework.security.core.userdetails.User user) {
        return null;
    }

    @Override
    public org.springframework.security.core.userdetails.User updateUser(org.springframework.security.core.userdetails.User user) {
        return null;
    }

    @Override
    public User addUser(User user) {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        return (user);
    }

    @Override
    public User updateUser(User user) {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        return (user);
    }

    @Override
    public void removeUser(Long idUsers) {

        userRepository.deleteById(idUsers);

    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public org.springframework.security.core.userdetails.User getUserByEmail(String email) {
        return null;
    }


}
