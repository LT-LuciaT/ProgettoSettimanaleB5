package it.epicode.ProgettoSettimanaleB5.repository;


import it.epicode.ProgettoSettimanaleB5.model.Postazione;
import it.epicode.ProgettoSettimanaleB5.model.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {

    Optional<Postazione> findByCodiceUnivoco(String codiceUnivoco);

    @Query("SELECT p FROM Postazione p WHERE p.tipo = :tipo AND p.edificio.citta = :citta")
    List<Postazione> findByTipoAndCitta(@Param("tipo") TipoPostazione tipo,
                                        @Param("citta") String citta);

    boolean existsByCodiceUnivoco(String codiceUnivoco);
}