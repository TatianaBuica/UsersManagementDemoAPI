package main.model;

import main.beans.entity.Party;
import main.beans.entity.UserProjectAssignment;
import main.repository.PartyRepository;
import main.repository.UserProjectAssignmentRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
public class UserProjectAssignmentModel {
    @Autowired private UserRepository userRepository;
    @Autowired private PartyRepository partyRepository;
    @Autowired private UserProjectAssignmentRepository userProjectAssignmentRepository;

    public UserProjectAssignmentModel() {}

    public Party getPartyByUserCnp(String cnp) {
        UserProjectAssignment userProjectAssignment = userProjectAssignmentRepository.findByCnp(cnp);
        return partyRepository.findById(userProjectAssignment.getPartyName());
    }

    public UserProjectAssignment getUserProjectAssignmentByUserCnp(String cnp) {
        return userProjectAssignmentRepository.findByCnp(cnp);
    }

    public void save(UserProjectAssignment object) {
        userProjectAssignmentRepository.save(object);
    }
}
