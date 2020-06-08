package main.rest;

import main.beans.entity.User;
import main.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired private UserModel userModel;

    @GetMapping("/findAllUsers")
    List<User> all() {
        return userModel.all();
    }

    @GetMapping("/findUserByCnp")
    User findUserByCnp(@RequestParam(name = "cnp") String cnp) {
        return userModel.findUserByCnp(cnp);
    }

    @PostMapping("/saveUserByDetails")
    void saveUserByDetails(@RequestParam(name = "cnp") String cnp, @RequestParam(name = "email") String email,
                           @RequestParam(name = "fName") String fName, @RequestParam(name = "lName") String lName,
                           @RequestParam(name = "address", required = false) String address,
                           @RequestParam(name = "password",  required = false) String password) {
        User user = new User(cnp, email, fName, lName, address, password);
        userModel.saveUserObject(user);
    }

    @PostMapping("/saveUserObject")
    void saveUserObject(@RequestBody User user) {
        userModel.saveUserObject(user);
    }

    @PostMapping("/deleteUserByDetails")
    void deleteUserObject(@RequestParam(name = "cnp") String cnp, @RequestParam(name = "email") String email,
                          @RequestParam(name = "fName") String fName, @RequestParam(name = "lName") String lName,
                          @RequestParam(name = "address") String address, @RequestParam(name = "password") String password) {
        User user = new User(cnp, email, fName, lName, address, password);
        userModel.deleteUserObject(user);
    }

    @PostMapping("/deleteUserObject")
    void deleteUserObject(@RequestBody User user) {
        userModel.deleteUserObject(user);
    }

    @PostMapping("/deleteUserByCnp")
    void deleteUserObject(@RequestParam(name = "cnp") String cnp) {
        User user = userModel.findUserByCnp(cnp);
        if (user != null) {
            userModel.deleteUserObject(user);
        }
    }
}
