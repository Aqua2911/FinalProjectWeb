package ca.qc.colval.finalprojectweb.BLL.Services.Implementations;

import ca.qc.colval.finalprojectweb.BLL.Models.DTO.PhoneDTO;
import ca.qc.colval.finalprojectweb.BLL.Models.Phone;
import ca.qc.colval.finalprojectweb.BLL.Repositories.PhoneRepository;
import ca.qc.colval.finalprojectweb.BLL.Services.Interfaces.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public Phone create(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public Phone save(PhoneDTO phone) {
        Phone newPhone = new Phone();
        newPhone.setPhoneNumber(phone.getPhoneNumber());
        newPhone.setContactId(phone.getContactId());

        return phoneRepository.save(newPhone);
    }

    @Override
    public void update(PhoneDTO phone) {

    }

    @Override
    public Optional<Phone> search(PhoneDTO phone) {
        return Optional.empty();
    }

    @Override
    public List<Phone> findPhoneByContactId(Long contactId) {
        return phoneRepository.findByContactId(contactId);
    }
}
