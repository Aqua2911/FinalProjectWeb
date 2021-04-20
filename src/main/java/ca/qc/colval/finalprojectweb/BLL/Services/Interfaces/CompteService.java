package ca.qc.colval.finalprojectweb.BLL.Services.Interfaces;

import ca.qc.colval.finalprojectweb.BLL.Models.Compte;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.CompteDTO;

public interface CompteService {
    Compte create(Compte compte);

    void addContact(Compte compte);
    void save(CompteDTO compte);

    void update(CompteDTO compte);
}
