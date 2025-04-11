package it.epicode.ProgettoSettimanaleB5.service;


import it.epicode.ProgettoSettimanaleB5.exceptions.PostazioneEsistenteException;
import it.epicode.ProgettoSettimanaleB5.exceptions.PostazioneNonTrovataException;
import it.epicode.ProgettoSettimanaleB5.model.Postazione;
import it.epicode.ProgettoSettimanaleB5.model.TipoPostazione;
import it.epicode.ProgettoSettimanaleB5.repository.PostazioneRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostazioneService {

    private final PostazioneRepository postazioneRepository;

    @Transactional
    public Postazione creaPostazione(Postazione postazione) {
        if (postazioneRepository.existsByCodiceUnivoco(postazione.getCodiceUnivoco())) {
            throw new PostazioneEsistenteException(
                    "Esiste già una postazione con codice: " + postazione.getCodiceUnivoco());
        }
        return postazioneRepository.save(postazione);
    }

    @Transactional(readOnly = true)
    public Postazione trovaPerCodice(String codiceUnivoco) {
        return postazioneRepository.findByCodiceUnivoco(codiceUnivoco)
                .orElseThrow(() -> new PostazioneNonTrovataException(
                        "Postazione non trovata con codice: " + codiceUnivoco));
    }

    @Transactional(readOnly = true)
    public List<Postazione> trovaPerTipoECitta(TipoPostazione tipo, String citta) {
        return postazioneRepository.findByTipoAndCitta(tipo, citta);
    }

    @Transactional(readOnly = true)
    public List<Postazione> listaTutte() {
        return postazioneRepository.findAll();
    }
}
