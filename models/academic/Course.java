package models.academic;

import models.users.Teacher;
import models.users.Student;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class Course implements Serializable {
    @Serial
    private static final long serialVersionUID = 4526868344201858927L;

    private UUID id;
    private String name;
    private int credits;
    private List<Teacher> teachers = new ArrayList<>();
    private List<Lesson> lessons = new ArrayList<>();
    private List<Student> enrolledStudents = new ArrayList<>();
    private String major;
    private int yearOfStudy;

    public Course() {}

    public Course(String name, int credits, String major, int yearOfStudy) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.major = major;
        this.yearOfStudy = yearOfStudy;
    }

    public void addTeacher(Teacher t) {
        if (t != null) {
            teachers.add(t);
        }
    }

    public void enrollStudent(Student s) {
        if (s != null) {
            enrolledStudents.add(s);
        }
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public int getCredits() { return credits; }
    public String getMajor() { return major; }
    public List<Teacher> getTeachers() { return teachers; }
    public List<Lesson> getLessons() { return lessons; }
    public List<Student> getEnrolledStudents() { return enrolledStudents; }
    public int getYearOfStudy() { return yearOfStudy; }

    public void setName(String name) { this.name = name; }
    public void setName(int credits) { this.credits = credits; }
    public void setMajor(String major) { this.major = major; }
    public void setYearOfStudy(int yearOfStudy) { this.yearOfStudy = yearOfStudy; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("COURSE | %s | Major: %s | Credits: %d | Year of Study: %d", name, major, credits, yearOfStudy);
    }
}