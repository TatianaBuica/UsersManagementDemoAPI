package main.rest;

import main.beans.entity.Party;
import main.model.UserProjectAssignmentModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProjectAssignmentController {
    UserProjectAssignmentModel userProjectAssignment = new UserProjectAssignmentModel();

    @GetMapping("/getPartyByUserCnp")
    Party getPartyByUserCnp(@RequestParam(name = "cnp") String cnp) {
        return userProjectAssignment.getPartyByUserCnp(cnp);
    }
}
