package it.epicode.ProgettoSettimanaleB5.repository;

import it.epicode.ProgettoSettimanaleB5.model.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Integer> {
}