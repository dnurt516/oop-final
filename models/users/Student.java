package models.users;



import enums.Role;

import exceptions.CreditLimitException;
import exceptions.FailLimitException;
import exceptions.LowHIndexException;

import models.academic.Course;
import models.academic.Mark;
import models.research.Researcher;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Student extends User {
    @Serial
    private static final long serialVersionUID = 4760794677432904168L;
    private double gpa;
    private int year;
    private Researcher researchSupervisor;

    private static final int MAX_CREDITS = 21;
    private static final int MAX_FAILS = 3;

    private int totalCredits = 0;
    private int failCount = 0;

    private String major;
    private Researcher supervisor;
    private List<Course> courses = new ArrayList<>();
    private Map<Course, Mark> transcript = new HashMap<>();

    public Student() {
        super();
    }

    public Student(String firstName, String lastName, String email, String passwordHash, int year, String major) {
        super(firstName, lastName, email, passwordHash, Role.STUDENT);
        this.year = year;
        this.major = major;
    }

    public List<Mark> viewMark() {
        return new ArrayList<>(transcript.values());
    }

    public double getGpa() {
        if (transcript.isEmpty()) return 0.0;

        double totalWeighetGpa = 0;
        int totalCredits = 0;

        for (Map.Entry<Course, Mark> entry : transcript.entrySet()) {
            Course course = entry.getKey();
            Mark mark = entry.getValue();

            totalWeighetGpa += mark.getGpaValue() * course.getCredits();
            totalCredits += course.getCredits();
        }

        this.gpa = (totalCredits == 0) ? 0.0 : totalWeighetGpa / totalCredits;
        return this.gpa;
    }

    public int getYear() { return year; }
    public Researcher getResearchSupervisor() { return researchSupervisor; }
    public int getTotalCredits() { return totalCredits; }
    public int getFailCount() { return failCount; }
    public String getMajor() { return major; }
    public Researcher getSupervisor() { return supervisor; }
    public List<Course> getCourses() { return courses; }
    public Map<Course, Mark> getTranscript() { return transcript; }

    public void setYear(int year) {
        this.year = year;
    }

    public void setResearchSupervisor(Researcher r) {
        if (r == null) {
            this.researchSupervisor = null;
            return;
        }

        if (r instanceof User u) {
            email = u.getEmail();
        }

        if (this.year == 4) {
            if (r.getHIndex() < 3) {
                throw new LowHIndexException(
                    r.getHIndex(),
                    3,
                    email
                );
            }
        } else {
            System.out.println("Only 4th year students can have a research supervisor.");
            return;
        }

        this.researchSupervisor = r;
        System.out.println("Supervisor successfully assigned!");
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setSupervisor(Researcher supervisor) {
        this.supervisor = supervisor;
    }

    public void registerCourse(Course c) throws CreditLimitException, FailLimitException {
        if (totalCredits + c.getCredits() > MAX_CREDITS) {
            throw new CreditLimitException();
        }
        if (failCount >= MAX_FAILS) {
            throw new FailLimitException();
        }

        this.addCourse(c);
        this.totalCredits += c.getCredits();

        c.enrollStudent(this);

        System.out.println("Successfully registered for: " + c.getName());
    }

    public void addCourse(Course c) {
        if (c != null) {
            this.courses.add(c);
        }
    }

    public void rateTeacher(Teacher t, int score) {
        if (t != null && score >= 1 && score <= 10) {
            t.addRating(this, score);
            System.out.println("Rating submitted for teacher: " + t.getLastName());
        } else {
            System.out.println("Invalid rating score. Use 1-10.");
        }
    }

    @Override
    public String getProfile() {
        return String.format("STUDENT | %s %s | Email: %s | Year: %d | GPA: %.2f", firstName, lastName, email, year, gpa);
    }

    @Override
    public String toString() {
        return getProfile();
    }
}