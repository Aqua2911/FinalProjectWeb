package ca.qc.colval.finalprojectweb.BLL.Repositories;

import ca.qc.colval.finalprojectweb.BLL.Models.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}
