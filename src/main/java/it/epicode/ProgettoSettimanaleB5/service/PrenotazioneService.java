package it.epicode.ProgettoSettimanaleB5.service;


import it.epicode.ProgettoSettimanaleB5.exceptions.PrenotazioneException;
import it.epicode.ProgettoSettimanaleB5.model.Prenotazione;
import it.epicode.ProgettoSettimanaleB5.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public void creaPrenotazione(Prenotazione prenotazione) throws PrenotazioneException {
        validaPrenotazione(prenotazione);
        prenotazioneRepository.save(prenotazione);
    }

    private void validaPrenotazione(Prenotazione prenotazione) throws PrenotazioneException {
        // Verifica se la data è nel passato
        if (prenotazione.getDataPrenotazione().isBefore(LocalDate.now())) {
            throw new PrenotazioneException("Non è possibile prenotare per date passate");
        }

        // Verifica se la postazione è già prenotata
        boolean postazioneOccupata = prenotazioneRepository
                .existsByPostazioneAndDataPrenotazione(
                        prenotazione.getPostazione(),
                        prenotazione.getDataPrenotazione());

        if (postazioneOccupata) {
            throw new PrenotazioneException("Postazione già prenotata per questa data");
        }

        // Verifica se l'utente ha già una prenotazione per quella data
        boolean utenteOccupato = prenotazioneRepository
                .existsByUtenteAndDataPrenotazione(
                        prenotazione.getUtente(),
                        prenotazione.getDataPrenotazione());

        if (utenteOccupato) {
            throw new PrenotazioneException("Hai già una prenotazione per questa data");
        }
    }

    public List<Prenotazione> trovaPrenotazioniPerUtente(String username) {
        return prenotazioneRepository.findByUtenteUsername(username);
    }

    public List<Prenotazione> trovaTuttePrenotazioni() {
        return prenotazioneRepository.findAll();
    }
}