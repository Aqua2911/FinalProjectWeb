package ca.qc.colval.finalprojectweb.BLL.Services.Implementations;

import ca.qc.colval.finalprojectweb.BLL.Models.DTO.GroupDTO;
import ca.qc.colval.finalprojectweb.BLL.Models.Group;
import ca.qc.colval.finalprojectweb.BLL.Repositories.GroupRepository;
import ca.qc.colval.finalprojectweb.BLL.Services.Interfaces.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group create(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group save(GroupDTO group) {
        Group newGroup = new Group();
        newGroup.setGroupName(group.getGroupName());
        newGroup.setCompteId(group.getCompteId());

        return groupRepository.save(newGroup);
    }

    @Override
    public void update(GroupDTO group) {

    }

    @Override
    public Optional<Group> search(GroupDTO group) {
        return groupRepository.findById(group.getGroupId());
    }

    @Override
    public List<Group> findGroupByCompteId(Long compteId) {
        return groupRepository.findByCompteId(compteId);
    }

    @Override
    public Optional<Group> readOne(Long id) {
        return groupRepository.findById(id);
    }
}
