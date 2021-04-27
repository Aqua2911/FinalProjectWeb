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
    public CompteServiceImpl(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Override
    public Compte create(Compte compte) {
        return compteRepository.save(compte);
    }

    @Override
    public void addContact(Compte compte) {

    }

    @Override
    public Compte save(CompteDTO compte) {

        Compte newCompte = new Compte();
        newCompte.setFirstName(compte.getFirstName());
        newCompte.setLastName(compte.getLastName());
        newCompte.setUsername(compte.getUsername());
        newCompte.setPassword(compte.getPassword());

        return compteRepository.save(newCompte);
    }

    @Override
    public void update(CompteDTO compte) {

    }
}
