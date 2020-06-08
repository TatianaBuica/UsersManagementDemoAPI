package main.management.party;

import main.beans.entity.Party;
import main.beans.Roles;
import main.beans.entity.User;
import main.beans.entity.UserProjectAssignment;
import main.model.UserProjectAssignmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyManagement {
    private @Autowired UserProjectAssignmentModel userProjectAssignmentModel;
    private User user;

    public PartyManagement() {}

    public PartyManagement(User user) {
        this.user = user;
    }

    public Party getUserParty() {
        return userProjectAssignmentModel.getPartyByUserCnp(user.getCnp());
    }

    public void changePartyOwner(String newOwnerCnp) {
        if (userHasAdminAccess()) {
            UserProjectAssignment userProjectAssignment = userProjectAssignmentModel.getUserProjectAssignmentByUserCnp(user.getCnp());
            userProjectAssignment.setRole(Roles.USER.name());
            userProjectAssignmentModel.save(userProjectAssignment);

            userProjectAssignment.setRole(Roles.ADMIN.name());
            userProjectAssignment.setCnp(newOwnerCnp);
            userProjectAssignmentModel.save(userProjectAssignment);
        }
    }

    private boolean userHasAdminAccess() {
        return userProjectAssignmentModel.getUserProjectAssignmentByUserCnp(user.getCnp()).getRole().equals(Roles.ADMIN.name());
    }
}
