package main.management;

import main.beans.UserException;
import main.beans.entity.User;

public interface UserActions {
    void addUser(User newUser) throws UserException;
    void deleteUser(User userToDelete) throws UserException;
}
