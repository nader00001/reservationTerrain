package tn.projet.spring.service;


import org.springframework.security.core.userdetails.User;

import java.util.List;


public interface UserService {

    public List<tn.projet.spring.Models.User> retrieveAllUser();
    public User addUser(User user);
    public User updateUser(User user);

    tn.projet.spring.Models.User  addUser(tn.projet.spring.Models.User user);

    tn.projet.spring.Models.User updateUser(tn.projet.spring.Models.User user);

    void removeUser(Long idUsers);

    tn.projet.spring.Models.User getUserById(Long id);

    User getUserByEmail(String email);

}
