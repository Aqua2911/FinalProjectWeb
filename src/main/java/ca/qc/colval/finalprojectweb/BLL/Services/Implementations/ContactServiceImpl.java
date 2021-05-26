package ca.qc.colval.finalprojectweb.BLL.Services.Implementations;

import ca.qc.colval.finalprojectweb.BLL.Models.Contact;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.ContactDTO;
import ca.qc.colval.finalprojectweb.BLL.Repositories.ContactRepository;
import ca.qc.colval.finalprojectweb.BLL.Services.Interfaces.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact create(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact save(ContactDTO contact) {
        Contact newContact = new Contact();
        newContact.setFirstName(contact.getFirstName());
        newContact.setLastName(contact.getLastName());
        newContact.setCompteId(newContact.getCompteId());

        return contactRepository.save(newContact);
    }

    @Override
    public void update(ContactDTO contact) {

    }

    @Override
    public Optional<Contact> search(ContactDTO contact) {
        return contactRepository.findByContactId(contact.getContactId());
    }

    /*@Override
    public List<Contact> findContactByCompteId(Long compteId) {
        return null;
    }

    @Override
    public void addPhone(Contact contact) {

    }*/
}
