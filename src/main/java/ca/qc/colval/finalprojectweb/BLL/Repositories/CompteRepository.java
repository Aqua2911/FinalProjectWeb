package ca.qc.colval.finalprojectweb.BLL.Repositories;

import ca.qc.colval.finalprojectweb.BLL.Models.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte, Long> {

    @Query("SELECT c FROM Compte c WHERE c.username = :username AND c.password = :password ORDER BY c.compteId")
    Optional<Compte> findByLogin(String username, String password);

    @Query("SELECT c FROM Compte c WHERE c.compteId = :compteId")
    Compte findByCompteId(Long compteId);

    @Transactional
    @Modifying
    @Query("UPDATE Compte c set c.credits = c.credits + :credits where c.compteId = :compteID")
    void addCredits(Long compteID, double credits);
}
