package ca.qc.colval.finalprojectweb.BLL.Services.Interfaces;

import ca.qc.colval.finalprojectweb.BLL.Models.Contact;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.ContactDTO;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    Contact create(Contact contact);

    Contact save(ContactDTO contact);

    void updateGroup(Contact contact);

    Optional<Contact> search(ContactDTO contact);

    List<Contact> findContactByCompteId(Long compteId);

    Optional<Contact> readOne(Long id);

    //void addPhone(Contact contact);
}
