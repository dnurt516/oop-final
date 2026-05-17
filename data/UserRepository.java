package data;

import models.users.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class UserRepository implements Serializable {

    @Serial
    private static final long serialVersionUID = -3476010976522340238L;

    public User findById(UUID id) {
        return DataBase.getInstance().getUsers().values().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User findByEmail(String email) {
        return DataBase.getInstance().getUsers().values().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public void save(User user) {
        if (user != null) {
            DataBase.getInstance().addUser(user);
        }
    }

    public void delete(UUID id) {
        if (id != null) {
            DataBase.getInstance().getUsers().remove(id);
        }
    }

}