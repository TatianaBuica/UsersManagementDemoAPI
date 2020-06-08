package main.repository;

import main.beans.entity.User;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<User, String> {
    List<User> findAll();
    User findByCnp(String cnp);
    User findByCnpAndPassword(String cnp, String password);
    void save(User user);
    void delete(User user);
}
