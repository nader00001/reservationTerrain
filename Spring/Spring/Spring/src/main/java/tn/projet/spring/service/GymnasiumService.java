package tn.projet.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.projet.spring.entity.Gymnasium;
import tn.projet.spring.repository.GymnasiumRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GymnasiumService {

    @Autowired
    private GymnasiumRepository gymnasiumRepository;

    // Créer une salle
    public Gymnasium createGymnasium(Gymnasium gymnasium) {
        return gymnasiumRepository.save(gymnasium);
    }

    // Lire toutes les salles
    public List<Gymnasium> getAllGymnasiums() {
        return gymnasiumRepository.findAll();
    }

    // Lire une salle par ID
    public Gymnasium getGymnasiumById(Long id) {
        return gymnasiumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gymnasium not found with ID: " + id));
    }

    // Mettre à jour une salle
    public Gymnasium updateGymnasium(Long id, Gymnasium updatedGymnasium) {
        Gymnasium existingGymnasium = gymnasiumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gymnasium not found"));
        existingGymnasium.setName(updatedGymnasium.getName());
        existingGymnasium.setLocation(updatedGymnasium.getLocation());
        existingGymnasium.setCapacity(updatedGymnasium.getCapacity());
        existingGymnasium.setType(updatedGymnasium.getType());
        return gymnasiumRepository.save(existingGymnasium);
    }


    // Supprimer une salle
    public void deleteGymnasium(Long id) {
        Gymnasium existingGymnasium = gymnasiumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gymnasium not found with ID: " + id));

        gymnasiumRepository.delete(existingGymnasium);
    }
}
