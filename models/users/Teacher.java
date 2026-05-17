package models.users;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import data.DataBase;

import enums.DayOfWeek;
import enums.Role;
import enums.TeacherTitle;

import models.academic.Course;
import models.academic.Mark;
import models.academic.Lesson;

public class Teacher extends Employee {
    @Serial
    private static final long serialVersionUID = 2192862731176899505L;

    private TeacherTitle title;
    private List<Course> courses;

    private double rating = 0.0;
    private int ratingCount = 0;
    private double sumRating = 0.0;
    private List<Student> ratedStudentIds = new ArrayList<>();

    private Map<DayOfWeek, List<Lesson>> schedule = new TreeMap<>();

    public Teacher() {
        super();
    }

    public Teacher(String firstName, String lastName, String email, String passwordHash,
                   String department, double salary, TeacherTitle title) {
        super(firstName, lastName, email, passwordHash, Role.TEACHER, department, salary);
        this.title = title;

        initializeSchedule();
    }

    private void initializeSchedule() {
        for (DayOfWeek day : DayOfWeek.values()) {
            schedule.put(day, new ArrayList<>());
        }
    }

    public void addLessonToSchedule(Lesson lesson) {
        schedule.get(lesson.getDayOfWeek()).add(lesson);
        schedule.get(lesson.getDayOfWeek()).sort(Comparator.comparing(Lesson::getDayOfWeek));
    }

    public void viewSchedule() {
        System.out.println("=== TEACHER'S SCHEDULE: " + getLastName() + " ===");
        schedule.forEach((day, lessons) -> {
            System.out.println(day + ":");
            if (lessons.isEmpty()) {
                System.out.println("  - Free day");
            } else {
                lessons.forEach(l -> System.out.println("  " + l.toString()));
            }
        });
    }

    public void putMark(Student s, Course c, Mark m) {
        if (!DataBase.getInstance().getTeacherCourses(this).contains(c)) {
            System.out.println("Error: You are not a teacher for this course!");
            return;
        }

        if (!c.getEnrolledStudents().contains(s)) {
            System.out.println("Error: Student is not enrolled in this course!");
            return;
        }

        s.getTranscript().put(c, m);

        System.out.println("Mark updated for " + s.getFirstName() + " " + s.getLastName());
    }

    public void addRating(Student s, int score) {
        if (ratedStudentIds.contains(s)) {
            System.out.println("You have already rated this teacher.");
            return;
        }

        this.sumRating += score;
        this.ratingCount++;
        this.rating = this.sumRating / this.ratingCount;
        ratedStudentIds.add(s);
    }

    public double getRating() {
        return rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public Map<DayOfWeek, List<Lesson>> getSchedule() {
        return schedule;
    }

    public List<Student> viewStudents(Course c) {
        if (c == null) {
            System.out.println("Course not found.");
            return new ArrayList<>();
        }

        if (!DataBase.getInstance().getTeacherCourses(this).contains(c)) {
            System.out.println("Error: You are not a teacher for this course!");
            return new ArrayList<>();
        }

        return c.getEnrolledStudents();
    }

    @Override
    public String getProfile() {
        return String.format("TEACHER | %s | Title: %s | Dept: %s | Salary: %f | Rating: %.1f (%d votes)", email, title, department, salary, rating, ratingCount);
    }

    @Override
    public String toString() {
        return getProfile();
    }
}