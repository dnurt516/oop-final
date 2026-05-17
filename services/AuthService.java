package services;

import data.DataBase;
import data.UserRepository;
import enums.Role;
import exceptions.AuthenticationException;
import models.users.User;
import utils.LogEntry;
import utils.PasswordHasher;

import java.util.*;

public class AuthService {

    private User currentUser;

    public AuthService() {
    }

    private final UserRepository repo = new UserRepository();


    public User login(String email, String password) {
        if (email == null || password == null) {
            System.out.println("Email or password cannot be null.");
            return null;
        }

        User user = repo.findByEmail(email);
        String hashedPassword = PasswordHasher.hashPassword(password);

        if (user == null) {
            throw new AuthenticationException(email, "User not found.");
        }

        if (user.getPassword().equals(hashedPassword)) {
            this.currentUser = user;

            DataBase.getInstance().addLog(new LogEntry(user.getId(), "User logged in successfully."));
            System.out.println("Welcome, " + user.getFirstName() + "!");
            return user;
        }

        throw new AuthenticationException();
    }

    public void logout(UUID userId) {
        if (currentUser != null && currentUser.getId().equals(userId)) {
            DataBase.getInstance().addLog(new LogEntry(userId, "User logged out."));
            System.out.println("Goodbye, " + currentUser.getFirstName() + ".");
            this.currentUser = null; // Сбрасываем текущего пользователя
        } else {
            System.out.println("No active session found for this user ID.");
        }
    }

    public void changePassword(UUID userId, String newPassword) {
        if (this.currentUser == null) {
            System.out.println("No active session. Please log in first.");
            return;
        }

        if (currentUser.getId().equals(userId) || currentUser.getRole() == Role.ADMIN) {
            if (newPassword == null || newPassword.trim().isEmpty()) {
                System.out.println("Password cannot be empty.");
                return;
            }

            User userToUpdate = repo.findById(userId);

            if (userToUpdate != null) {
                userToUpdate.setPassword(newPassword);

                repo.save(userToUpdate);

                DataBase.getInstance().addLog(new LogEntry(userId, "Password changed successfully."));
                System.out.println("Password updated successfully.");
            } else {
                System.out.println("User not found.");
            }
        } else {
            System.out.println("Permission denied!");
        }
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

}