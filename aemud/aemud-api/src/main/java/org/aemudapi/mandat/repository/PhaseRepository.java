package org.aemudapi.mandat.repository;

import org.aemudapi.mandat.entity.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, String> {

    /**
     * Trouve la phase en cours à une date donnée.
     * @param date La date actuelle.
     * @return Un Optional contenant la phase en cours si elle existe.
     */
    @Query("SELECT p FROM Phase p WHERE p.mandat.id = :mandatId AND p.dateDebut <= :date AND p.dateFin >= :date")
    Optional<Phase> findCurrentPhaseForMandat(String mandatId, LocalDate date);

    /**
     * Trouve toutes les phases qui sont terminées avant une date donnée.
     * @param date La date actuelle.
     * @return Une liste des phases passées.
     */
    @Query("SELECT p FROM Phase p WHERE p.dateFin < :date ORDER BY p.dateFin DESC")
    List<Phase> findPastPhases(LocalDate date);

    /**
     * Trouve la prochaine phase qui n'a pas encore commencé.
     * @param date La date actuelle.
     * @return Un Optional contenant la prochaine phase si elle existe.
     */
    @Query("SELECT p FROM Phase p WHERE p.dateDebut > :date ORDER BY p.dateDebut ASC")
        List<Phase> findFuturePhases(LocalDate date);

    default Optional<Phase> findNextPhase(LocalDate date) {
        return findFuturePhases(date).stream().findFirst();
    }

    List<Phase> findByMandatId(String mandatId);

}
