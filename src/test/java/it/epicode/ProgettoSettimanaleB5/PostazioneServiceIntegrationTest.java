package it.epicode.ProgettoSettimanaleB5;

import it.epicode.ProgettoSettimanaleB5.exceptions.PostazioneEsistenteException;
import it.epicode.ProgettoSettimanaleB5.model.Edificio;
import it.epicode.ProgettoSettimanaleB5.model.Postazione;
import it.epicode.ProgettoSettimanaleB5.model.TipoPostazione;
import it.epicode.ProgettoSettimanaleB5.repository.EdificioRepository;
import it.epicode.ProgettoSettimanaleB5.service.PostazioneService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
public class PostazioneServiceIntegrationTest {

    @Autowired
    private PostazioneService postazioneService;

    @Autowired
    private EdificioRepository edificioRepository;

    @Test
    void quandoCodiceUnivocoEsistente_alloraLanciaEccezione() {
        Edificio edificio = edificioRepository.save(new Edificio(null, "Nome", "Indirizzo", "Citta", null));

        Postazione postazione1 = Postazione.builder()
                .codiceUnivoco("P001")
                .descrizione("Postazione test")
                .tipo(TipoPostazione.PRIVATO)
                .maxOccupanti(1)
                .edificio(edificio)
                .build();

        postazioneService.creaPostazione(postazione1);

        Postazione postazione2 = Postazione.builder()
                .codiceUnivoco("P001") // Stesso codice
                .descrizione("Altra postazione")
                .tipo(TipoPostazione.OPENSPACE)
                .maxOccupanti(4)
                .edificio(edificio)
                .build();

        assertThrows(PostazioneEsistenteException.class,
                () -> postazioneService.creaPostazione(postazione2));
    }
}