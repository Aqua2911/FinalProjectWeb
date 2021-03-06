package ca.qc.colval.finalprojectweb.BLL.Repositories;

import ca.qc.colval.finalprojectweb.BLL.Models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    @Query("SELECT p FROM Phone p WHERE p.contactId = :contactId")
    List<Phone> findByContactId(Long contactId);
}