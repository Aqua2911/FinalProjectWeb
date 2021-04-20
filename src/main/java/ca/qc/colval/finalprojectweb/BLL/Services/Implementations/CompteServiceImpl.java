package ca.qc.colval.finalprojectweb.BLL.Services.Implementations;

import ca.qc.colval.finalprojectweb.BLL.Models.Compte;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.CompteDTO;
import ca.qc.colval.finalprojectweb.BLL.Repositories.CompteRepository;
import ca.qc.colval.finalprojectweb.BLL.Services.Interfaces.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteServiceImpl implements CompteService {
    private final CompteRepository compteRepository;

    @Autowired
    public CompteServiceImpl(CompteRepository customerRepository) {
        this.compteRepository = customerRepository;
    }

    @Override
    public Compte create(Compte compte) {
        return compteRepository.save(compte);
    }

    @Override
    public void addContact(Compte compte) {

    }

    @Override
    public void save(CompteDTO compte) {

    }

    @Override
    public void update(CompteDTO compte) {

    }
}
