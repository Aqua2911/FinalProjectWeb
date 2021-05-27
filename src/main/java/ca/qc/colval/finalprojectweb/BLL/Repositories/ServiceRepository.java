package ca.qc.colval.finalprojectweb.BLL.Repositories;

import ca.qc.colval.finalprojectweb.BLL.Models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query("SELECT s FROM Service s WHERE s.compteId = :compteId And s.contactId = :contactId")
    List<Service> findByCompteIdAndContactId(Long compteId, Long contactId);
}

