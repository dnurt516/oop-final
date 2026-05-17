package models.users;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import java.util.Objects;

import data.DataBase;
import enums.Language;
import enums.Role;
import utils.PasswordHasher;
import utils.Ticket;

public abstract class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 6610072146833023323L;

    protected UUID id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String passwordHash;
    protected Language language;
    protected Role role;
    protected Date createdAt;

    public User() {}

    public User(String firstName, String lastName, String email, String password, Role role) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = PasswordHasher.hashPassword(password);
        this.role = role;
    }

    public abstract String getProfile();

    public UUID getId() { return id; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Language getLanguage() { return language; }
    public String getPassword() { return this.passwordHash; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setLanguage(Language language) { this.language = language; }
    public void setPassword(String password) {
        this.passwordHash = PasswordHasher.hashPassword(password);
    }

    public void sendTicket(String description) {
        if (description == null || description.trim().isEmpty()) {
            System.out.println("Ticket description cannot be empty");
            return;
        }

        Ticket t = new Ticket(this, description);

        DataBase.getInstance().getTickets().add(t);

        System.out.println("Your ticket has been sent to TechSupport. Wait for the answer.");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        User user = (User) obj;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", language=" + language +
                ", role=" + role +
                ", createdAt=" + createdAt +
                '}';
    }
}