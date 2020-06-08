package main.management.admin;

import main.beans.Roles;
import main.beans.UserException;
import main.beans.entity.Party;
import main.beans.entity.User;
import main.beans.entity.UserProjectAssignment;
import main.management.UserActions;
import main.model.PartyModel;
import main.model.UserModel;
import main.model.UserProjectAssignmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminManagement implements UserActions {
    private @Autowired UserModel userModel;
    private @Autowired PartyModel partyModel;
    private @Autowired UserProjectAssignmentModel userProjectAssignmentModel;
    private User user;
    private Party owningParty;
    private UserProjectAssignment userProjectAssignment;

    public AdminManagement() {}

    public AdminManagement(User user) {
        this.user = user;
        assert false;
        userProjectAssignment = userProjectAssignmentModel.getUserProjectAssignmentByUserCnp(user.getCnp());
        owningParty = userProjectAssignmentModel.getPartyByUserCnp(user.getCnp());
    }

    public void createParty(Party party) {
        if (userHasAdminAccess()) {
            partyModel.savePartyObject(party);
        }
    }

    public void deleteParty(Party party) {
        if (userHasAdminAccess()) {
            partyModel.deletePartyObject(party);
        }
    }

    public void changePartyOwner(String newOwnerCnp, boolean changeCurrentUserRoleFromAdmin) {
        if (userHasAdminAccess()) {
            UserProjectAssignment userProjectAssignment = userProjectAssignmentModel.getUserProjectAssignmentByUserCnp(user.getCnp());
            if (changeCurrentUserRoleFromAdmin) {
                userProjectAssignment.setRole(Roles.USER.name());
            }
            userProjectAssignmentModel.save(userProjectAssignment);

            userProjectAssignment.setRole(Roles.ADMIN.name());
            userProjectAssignment.setCnp(newOwnerCnp);
            userProjectAssignmentModel.save(userProjectAssignment);
        }
    }

    public void addUser(User newUser) throws UserException {
        if (userHasAdminAccess()) {
            userModel.saveUserObject(newUser);
        } else {
            throw new UserException(String.format("User %s is not allowed to 'add user'!", user.getCnp()));
        }
    }

    public void deleteUser(User userToDelete) throws UserException {
        if (userHasAdminAccess()) {
            userModel.deleteUserObject(userToDelete);
        } else {
            throw new UserException(String.format("User %s is not allowed to 'delete user'!", user.getCnp()));
        }
    }

    private boolean userHasAdminAccess() {
        return userProjectAssignmentModel.getUserProjectAssignmentByUserCnp(user.getCnp()).getRole().equals(Roles.ADMIN.name());
    }
}
