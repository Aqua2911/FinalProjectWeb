package ca.qc.colval.finalprojectweb.BLL.Repositories;

import ca.qc.colval.finalprojectweb.BLL.Models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT c FROM Contact c WHERE c.contactId = :contactId")
    Optional<Contact> findByContactId(Long contactId);

    @Query("SELECT c FROM Contact c WHERE c.compteId = :compteId")
    List<Contact> findByCompteId(Long compteId);

    @Transactional
    @Modifying
    @Query("UPDATE Contact c set c.groupId = :groupId where c.contactId = :contactId")
    void updateGroup(Long contactId, Long groupId);
}