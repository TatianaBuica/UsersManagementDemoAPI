package main.rest;

import main.beans.entity.Party;
import main.beans.entity.User;
import main.repository.PartyRepository;
import main.repository.UserProjectAssignmentRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserManagementController {
    @Autowired private UserRepository repository;
    @Autowired private PartyRepository partyRepository;
    @Autowired private UserProjectAssignmentRepository userProjectAssignmentRepository;

    @GetMapping("/userLogin")
    User userLogin(@RequestParam(name = "user") String user, @RequestBody String password) {
        return repository.findByCnpAndPassword(user, password);
    }

    @GetMapping("/listUsersByPartyName")
    List<User> listUsersByPartyName(@RequestParam(name = "partyName") String partyName) {
        Party party = partyRepository.findById(partyName);
        return party.getUsers();
    }

    @GetMapping("/listPartyUsers")
    List<User> listPartyUsers(@RequestParam(name = "partyName") String partyName) {
        Party party = partyRepository.findByMainAddress(partyName);
        return party.getUsers();
    }
}
