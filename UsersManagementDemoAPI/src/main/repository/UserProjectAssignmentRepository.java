package main.repository;

import main.beans.entity.UserProjectAssignment;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserProjectAssignmentRepository extends Repository<UserProjectAssignment, String> {
    UserProjectAssignment findByCnp(String cnp);
    List<UserProjectAssignment> findByPartyName(String partyName);
    void save(UserProjectAssignment object);
}
