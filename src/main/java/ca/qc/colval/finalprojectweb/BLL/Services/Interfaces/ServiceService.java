package ca.qc.colval.finalprojectweb.BLL.Services.Interfaces;
import ca.qc.colval.finalprojectweb.BLL.Models.Compte;
import ca.qc.colval.finalprojectweb.BLL.Models.Contact;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.ServiceDTO;
import ca.qc.colval.finalprojectweb.BLL.Models.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    Service create(Service service);

    Service save(ServiceDTO service);

    void update(ServiceDTO service);

    Optional<Service> search(ServiceDTO service);

    List<Service> findByCompteIdAndContactId(Long compteId, Long contactId);

    Optional<Service> readOne(Long id);

    void addCall(Long compteId, Long contactId);

    void addSMS(Long compteId, Long contactId);
}
