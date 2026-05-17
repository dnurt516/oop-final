package services;

import data.DataBase;
import enums.ResolveStatus;
import enums.Role;
import models.users.Employee;
import utils.LogEntry;
import utils.Ticket;

import javax.xml.crypto.Data;
import java.io.Serial;
import java.util.*;
import java.util.stream.Collectors;

public class TechSupport extends Employee {
    @Serial
    private static final long serialVersionUID = -2372841868373398984L;

    private List<Ticket> openTickets;

    public TechSupport() {
        super();
    }

    public TechSupport(String firstName, String lastName, String email, String passwordHash, String department, double salary) {
        super(firstName, lastName, email, passwordHash, Role.TECH_SUPPORT, department, salary);
    }

    public List<Ticket> viewTickets() {
        return DataBase.getInstance().getTickets();
    }

    public List<Ticket> viewTicketsByStatus(ResolveStatus r) {
        return DataBase.getInstance().getTickets().stream()
                .filter(ticket -> ticket.getResolved().equals(r))
                .collect(Collectors.toList());
    }

    public void resolveTicket(Ticket t) {
        if (t == null) {
            System.out.println("Invalid ticket.");
            return;
        }

        t.setResolved(ResolveStatus.RESOLVED);

        String logMessage = String.format("TechSupport %s RESOLVED ticket from %s: '%s' ",
                this.getEmail(),
                t.getCreatedBy().getEmail(),
                t.getDescription());

        DataBase.getInstance().addLog(new LogEntry(this.getId(), logMessage));
        System.out.println("The ticket has been successfully resolved and is awaiting review.");
    }

    public void cancelTicket(Ticket t, String reason) {
        if (t == null) {
            System.out.println("Invalid ticket.");
            return;
        }

        t.setResolved(ResolveStatus.CANCELED);

        String logMessage = String.format("TechSupport %s CANCELED ticket from %s: '%s'. The reason is: %s",
                this.getEmail(),
                t.getCreatedBy().getEmail(),
                t.getDescription(),
                reason.trim().isEmpty() ? "unknown" : reason );

        DataBase.getInstance().addLog(new LogEntry(this.getId(), logMessage));
        System.out.println("The ticket has been canceled");
    }

    public void takeTicket(Ticket t, String comment) {
        if (t == null) {
            System.out.println("Invalid ticket.");
            return;
        }

        t.setResolved(ResolveStatus.IN_PROCESS);

        String logMessage = String.format("TechSupport %s took on the task of resolving the ticket from %s: '%s'. Comment: %s",
                this.getEmail(),
                t.getCreatedBy().getEmail(),
                t.getDescription(),
                comment.trim().isEmpty() ? "No comments" : comment);
    }
}