package ca.qc.colval.finalprojectweb.BLL.Services.Interfaces;

import ca.qc.colval.finalprojectweb.BLL.Models.Contact;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.ContactDTO;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.GroupDTO;
import ca.qc.colval.finalprojectweb.BLL.Models.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    Group create(Group group);

    Group save(GroupDTO group);

    void update(GroupDTO group);

    Optional<Group> search(GroupDTO group);

    List<Group> findGroupByCompteId(Long compteId);

    Optional<Group> readOne(Long id);
}