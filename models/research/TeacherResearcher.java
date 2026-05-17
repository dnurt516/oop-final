package models.research;

import enums.TeacherTitle;
import models.users.Teacher;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class TeacherResearcher extends Teacher implements Researcher {
    @Serial
    private static final long serialVersionUID = -1119377686338304650L;

    private final ResearcherBase researcherLogic = new ResearcherBase();

    public TeacherResearcher(String firstName, String lastName, String email, String passwordHash, String department, double salary, TeacherTitle title) {
        super(firstName, lastName, email, passwordHash, department, salary, title);
    }

    @Override public int getHIndex() { return researcherLogic.getHIndex(); }
    @Override public void addPaper(ResearchPaper p) { researcherLogic.addPaper(p); }
    @Override public void printPapers(Comparator<ResearchPaper> c) { researcherLogic.printPapers(c); }
    @Override public List<ResearchProject> getProjects() { return researcherLogic.getProjects(); }
    @Override public List<ResearchPaper> getPapers() { return researcherLogic.getPapers(); }

}