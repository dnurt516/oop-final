package models.users;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import data.DataBase;

import enums.DayOfWeek;
import enums.LessonType;
import models.academic.Lesson;
import utils.Report;
import utils.News;
import utils.Request;

import enums.ManagerType;
import enums.Role;

import models.academic.Course;

import javax.xml.crypto.Data;

public class Manager extends Employee {
    @Serial
    private static final long serialVersionUID = 8357840095460756140L;

    private ManagerType type;

    public Manager() {
        super();
    }

    public Manager(String firstName, String lastName, String email, String passwordHash, String department, double salary, ManagerType type) {
        super(firstName, lastName, email, passwordHash, Role.MANAGER, department, salary);

        this.type = type;
    }

    public void approveRegistration(Student s, Course c) {
        try {
            s.registerCourse(c);
        } catch (Exception e) {
            System.out.println("Error during approval" + e.getMessage());
        }
    }

    public void addCourseForRegistration(Course c, String major, int year) {
        c.setMajor(major);
        c.setYearOfStudy(year);

        DataBase.getInstance().addCourse(c);
    }

    public void assignTeacher(Teacher t, Course c) {
        if (c != null) {
            c.addTeacher(t);
            System.out.println("Teacher " + t.lastName + " assigned to " + c.getName());
        }
    }

    public Report generateReport() {
        return new Report();
    }

    public void postNews(String title, String content) {
        News news = new News(title, content, this);
        DataBase.getInstance().addNews(news);
    }

    public List<Student> viewStudentsSortedByGpa() {
        return DataBase.getInstance().getStudentsSortedByGpa();
    }

    public List<Student> viewStudentsSortedByName() {
        return DataBase.getInstance().getAllUsersByType(Student.class).stream()
                .sorted(Comparator.comparing(Student::getLastName)
                        .thenComparing(Student::getFirstName))
                .collect(Collectors.toList());
    }

    public List<Request> viewRequests() {
        return DataBase.getInstance().getRequests().stream()
                .filter(Request::isNew)
                .collect(Collectors.toList());
    }

    public void handleRequest(Request req, boolean approve) {
        if (approve) {
            req.accept();
            System.out.println("Request [" + req.toString() + "] accepted.");
        } else {
            req.reject();
            System.out.println("Request [" + req.toString() + "] rejected.");
        }
    }

    public void createLesson(Course course, Teacher teacher, LessonType type, DayOfWeek day, String time, String room) {
        Lesson newLesson = new Lesson(course, teacher, type, day, time, room);

        DataBase.getInstance().addLesson(newLesson);

        teacher.addLessonToSchedule(newLesson);

        System.out.println("Lesson created: " + course.getName() + " in room " + room);
    }

    @Override
    public String getProfile() {
        return String.format("MANAGER | %s | Type: %s | Dept: %s | Salary: %f", email, type, department, salary);
    }

    @Override
    public String toString() {
        return getProfile();
    }
}