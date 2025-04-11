package it.epicode.ProgettoSettimanaleB5.repository;


import it.epicode.ProgettoSettimanaleB5.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    Optional<Utente> findByUsername(String username);

    Optional<Utente> findByEmail(String email);
}