package ca.qc.colval.finalprojectweb.BLL.Services.Implementations;

import ca.qc.colval.finalprojectweb.BLL.Models.Compte;
import ca.qc.colval.finalprojectweb.BLL.Models.Contact;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.ServiceDTO;
import ca.qc.colval.finalprojectweb.BLL.Models.Service;
import ca.qc.colval.finalprojectweb.BLL.Repositories.ServiceRepository;
import ca.qc.colval.finalprojectweb.BLL.Services.Interfaces.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Service create(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public Service save(ServiceDTO service) {
        Service newService = new Service();
        newService.setServiceType(service.getServiceType());
        newService.setServiceDate(service.getServiceDate());
        newService.setCompteId(service.getCompteId());
        newService.setContactId(service.getContactId());

        return serviceRepository.save(newService);
    }

    @Override
    public void update(ServiceDTO service) {

    }

    @Override
    public Optional<Service> search(ServiceDTO service) {
        return serviceRepository.findById(service.getServiceId());
    }

    @Override
    public List<Service> findByCompteId(Long compteId) {
        return serviceRepository.findByCompteId(compteId);
    }

    @Override
    public List<Service> findByCompteIdAndContactId(Long compteId, Long contactId) {
        return serviceRepository.findByCompteIdAndContactId(compteId, contactId);
    }

    @Override
    public Optional<Service> readOne(Long id) {
        return serviceRepository.findById(id);
    }

    @Override
    public void addCall(Long compteId, Long contactId) {
        Service newCall = new Service();
        newCall.setServiceType("Call");
        newCall.setServiceDate(new Date());
        double random = ThreadLocalRandom.current().nextDouble(0, 10);
        double cost = Math.round(random*100.0)/100.0;
        newCall.setCost(cost);
        newCall.setContactId(contactId);
        newCall.setCompteId(compteId);

        serviceRepository.save(newCall);
    }

    @Override
    public void addSMS(Long compteId, Long contactId) {
        Service newSMS = new Service();
        newSMS.setServiceType("SMS");
        newSMS.setServiceDate(new Date());
        double random = ThreadLocalRandom.current().nextDouble(0, 2);
        double cost = Math.round(random*100.0)/100.0;
        newSMS.setCost(cost);
        newSMS.setContactId(contactId);
        newSMS.setCompteId(compteId);

        serviceRepository.save(newSMS);
    }
}
