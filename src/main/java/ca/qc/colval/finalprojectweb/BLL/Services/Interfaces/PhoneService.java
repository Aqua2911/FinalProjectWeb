package ca.qc.colval.finalprojectweb.BLL.Services.Interfaces;

import ca.qc.colval.finalprojectweb.BLL.Models.DTO.PhoneDTO;
import ca.qc.colval.finalprojectweb.BLL.Models.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneService {
    Phone create(Phone phone);

    Phone  save(PhoneDTO phone);

    void update(PhoneDTO phone);

    Optional<Phone> search(PhoneDTO phone);

    List<Phone> findPhoneByContactId(Long contactId);
}
