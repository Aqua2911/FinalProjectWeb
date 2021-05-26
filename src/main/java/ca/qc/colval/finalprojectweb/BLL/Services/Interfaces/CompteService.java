package ca.qc.colval.finalprojectweb.BLL.Services.Interfaces;

import ca.qc.colval.finalprojectweb.BLL.Models.Compte;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.CompteDTO;

import java.util.Optional;

public interface CompteService {
    Compte create(Compte compte);

    void addContact(Compte compte);

    Compte  save(CompteDTO compte);

    void update(CompteDTO compte);

    Optional<Compte> search(CompteDTO compte);

    Compte findCompteById(Long compteId);

    void addCredits(Compte compte);
}
