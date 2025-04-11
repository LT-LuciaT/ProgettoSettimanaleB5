package it.epicode.ProgettoSettimanaleB5.service;


import it.epicode.ProgettoSettimanaleB5.model.Utente;
import it.epicode.ProgettoSettimanaleB5.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public void registraUtente(Utente utente) {
        utenteRepository.save(utente);
    }

    public List<Utente> trovaTuttiUtenti() {
        return utenteRepository.findAll();
    }

    public Utente trovaUtentePerUsername(String username) {
        return utenteRepository.findByUsername(username).orElse(null);
    }
}