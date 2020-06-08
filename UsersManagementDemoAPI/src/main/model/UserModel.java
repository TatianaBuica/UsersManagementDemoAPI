package main.model;

import main.beans.entity.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserModel {
    @Autowired private UserRepository repository;

    public List<User> all() {
        return repository.findAll();
    }

    public User findUserByCnp(String cnp) {
        return repository.findByCnp(cnp);
    }

    public void saveUserByDetails(String cnp, String email, String fName, String lName, String address, String password) {
        User user = new User(cnp, email, fName, lName, address, password);
        repository.save(user);
    }

    public void saveUserObject(User user) {
        repository.save(user);
    }

    public void deleteUserObject(String cnp, String email, String fName, String lName, String address, String password) {
        User user = new User(cnp, email, fName, lName, address, password);
        repository.delete(user);
    }

    public void deleteUserObject(User user) {
        repository.delete(user);
    }

    public void deleteUserObject(String cnp) {
        User user = repository.findByCnp(cnp);
        if (user != null) {
            repository.delete(user);
        }
    }
}
