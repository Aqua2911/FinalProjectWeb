package ca.qc.colval.finalprojectweb.BLL.Services.Implementations;

import ca.qc.colval.finalprojectweb.BLL.Models.DTO.PhoneDTO;
import ca.qc.colval.finalprojectweb.BLL.Models.Phone;
import ca.qc.colval.finalprojectweb.BLL.Services.Interfaces.PhoneService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Override
    public Phone create(Phone phone) {
        return null;
    }

    @Override
    public Phone save(PhoneDTO phone) {
        return null;
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
        return null;
    }
}
