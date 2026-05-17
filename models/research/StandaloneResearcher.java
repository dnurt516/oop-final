package models.research;

import enums.Role;
import enums.TeacherTitle;
import models.users.Employee;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class StandaloneResearcher extends Employee implements Researcher, Serializable {
    @Serial
    private static final long serialVersionUID = -2355623964375771495L;

    private final ResearcherBase researcherLogic = new ResearcherBase();

    public StandaloneResearcher(String firstName, String lastName, String email, String passwordHash, Role role, String department, double salary) {
        super(firstName, lastName, email, passwordHash, role, department, salary);
    }

    @Override public int getHIndex() { return researcherLogic.getHIndex(); }
    @Override public void addPaper(ResearchPaper p) { researcherLogic.addPaper(p); }
    @Override public void printPapers(Comparator<ResearchPaper> c) { researcherLogic.printPapers(c); }
    @Override public List<ResearchProject> getProjects() { return researcherLogic.getProjects(); }
    @Override public List<ResearchPaper> getPapers() { return researcherLogic.getPapers(); }
}