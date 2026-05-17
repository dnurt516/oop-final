package models.users;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import data.DataBase;

import enums.UrgencyLevel;
import utils.Complaint;
import utils.Message;
import utils.Request;

import enums.Language;
import enums.Role;

public class Employee extends User {
    @Serial
    private static final long serialVersionUID = -9172925390220319508L;

    protected String department;
    protected double salary;
    protected List<Message> inbox = new ArrayList<>();

    public Employee() {
        super();
    }

    public Employee(String firstName, String lastName, String email, String passwordHash, Role role, String department, double salary) {
        super(firstName, lastName, email, passwordHash, role);

        this.department = department;
        this.salary = salary;
    }

    public void sendMessage(Employee to, String text) {
        if (to != null) {
            Message msg = new Message(this, to, text);
            to.addMessage(msg);

            System.out.println("Message set to " + to.email);
        }
    }

    public void addMessage(Message msg) {
        this.inbox.add(msg);
    }

    public void sendComplaint(String text, UrgencyLevel level) {
        Complaint complaint = new Complaint(this, text, level);

        DataBase.getInstance().addComplaint(complaint);

        System.out.println("The complaint has been sent and will be checked by a manager.");
    }

    public List<Message> viewMessages() {
        if (inbox.isEmpty()) {
            System.out.println("There are no messages.");
        }

        return inbox;
    }

    public void sendRequest(String description) {
        Request newRequest = new Request(this, description);
        DataBase.getInstance().addRequest(newRequest);
        System.out.println("Request sent to managers");
    }

    @Override
    public String getProfile() {
        return String.format("EMPLOYEE | %s | Dept: %s | Role: %s | Salary: %f", email, department, role, salary);
    }

    @Override
    public String toString() {
        return getProfile();
    }
}