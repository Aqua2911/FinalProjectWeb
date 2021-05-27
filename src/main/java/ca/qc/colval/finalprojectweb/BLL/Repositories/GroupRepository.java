package ca.qc.colval.finalprojectweb.BLL.Repositories;

import ca.qc.colval.finalprojectweb.BLL.Models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("SELECT g FROM Group g WHERE g.compteId = :compteId")
    List<Group> findByCompteId(Long compteId);
}
