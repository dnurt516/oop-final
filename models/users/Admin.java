package models.users;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import data.DataBase;
import data.UserRepository;
import enums.Role;
import utils.LogEntry;

public class Admin extends Employee {

    @Serial
    private static final long serialVersionUID = 6023011292040456631L;

    private final UserRepository repo = new UserRepository();

    public Admin() {
        super();
    }

    public Admin(String firstName, String lastName, String email, String passwordHash, String department, double salary) {
        super(firstName, lastName, email, passwordHash, Role.ADMIN, department, salary);
    }

    public void addUser(User u) {
        if (u != null) {
            repo.save(u);
            System.out.println("User added: " + u.getEmail());
        }
    }

    public void removeUser(UUID id) {
        repo.delete(id);
        System.out.println("User with ID " + id + " removed.");
    }

    public void updateUser(User u) {
        if (u != null && repo.findById(u.getId()) != null) {
            repo.save(u);
            System.out.println("User updated: " + u.getEmail());
        }
    }

    public List<LogEntry> viewLogs() {
        return DataBase.getInstance().getLogs();
    }
}