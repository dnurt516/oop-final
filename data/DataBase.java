package data;

import models.academic.Lesson;
import models.research.Researcher;
import models.research.ResearchPaper;
import models.research.ResearchProject;
import models.users.Employee;
import models.users.User;
import models.users.Teacher;
import models.users.Student;
import models.academic.Course;


import utils.*;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class DataBase implements Serializable {
    @Serial
    private static final long serialVersionUID = 1510290966022284918L;

    private static DataBase instance;
    private static final String FILE_NAME = "database.ser";

    // Users
    private Map<UUID, User> storage = new ConcurrentHashMap<>();

    // Academic
    private final List<Course> courses = new CopyOnWriteArrayList<>();
    private final List<Lesson> lessons = new CopyOnWriteArrayList<>();

    // Utils
    private final List<Complaint> complaints = new CopyOnWriteArrayList<>();
    private final List<LogEntry> logs = new CopyOnWriteArrayList<>();
    private final List<News> news = new CopyOnWriteArrayList<>();
    private final List<Request> requests = new CopyOnWriteArrayList<>();
    private final List<Ticket> tickets = new CopyOnWriteArrayList<>();

    // Researcher
    private final List<ResearchPaper> researchPapers = new CopyOnWriteArrayList<>();
    private final List<ResearchProject> researchProjects = new CopyOnWriteArrayList<>();

    public DataBase() {}

    public static DataBase getInstance() {
        if (instance == null) {
            instance = loadFromFile();
            if (instance == null) {
                instance = new DataBase();
            }
        }
        return instance;
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(this);
            System.out.println("Data successfully saved to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error saving database: " + e.getMessage());
        }
    }

    private static DataBase loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (DataBase) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading database, creating new one: " + e.getMessage());
            return null;
        }
    }

    // === USER MANAGEMENT ===

    public Map<UUID, User> getUsers() { return storage; }

    public User getUserById(UUID id) {
        return storage.get(id);
    }

    public User getUserByEmail(String email) {
        return storage.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public <T extends User> List<T> getAllUsersByType(Class<T> type) {
        return storage.values().stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsSortedByGpa() {
        return getAllUsersByType(Student.class).stream()
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .toList();
    }

    public void addUser(User user) {
        storage.put(user.getId(), user);
    }

    public void deleteUserById(UUID id) {
        storage.remove(id);
    }

    // === END OF USER MANAGEMENT ===

    // === LOGS MANAGEMENT ===

    public List<LogEntry> getLogs() { return logs; }

    public void addLog(LogEntry log) { this.logs.add(log); }

    // === COURSE MANAGEMENT ===

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course c) {
        this.courses.add(c);
    }

    // === END OF COURSE MANAGEMENT ===

    // === REQUEST MANAGEMENT ===

    public List<Request> getRequests() {
        return requests;
    }

    public void addRequest(Request r) {
        this.requests.add(r);
    }

    // === END OF REQUEST MANAGEMENT ===

    // === RESEARCH MANAGEMENT ===

    public List<Researcher> getAllResearchers() {
        return storage.values().stream()
                .filter(user -> user instanceof Researcher)
                .map(user -> (Researcher) user)
                .collect(Collectors.toList());
    }

    public List<ResearchPaper> getPapers() {
        return this.researchPapers;
    }

    public List<ResearchProject> getResearchProjects() {
        return this.researchProjects;
    }

    // === END OF RESEARCHER ===

    // === LESSON MANAGEMENT ===

    public List<Lesson> getLessons() { return lessons; }

    public List<Lesson> getLessonsByCourse(Course c) {
        return DataBase.getInstance().getLessons().stream()
                .filter(lesson -> lesson.getCourse().equals(c))
                .collect(Collectors.toList());
    }

    public void addLesson(Lesson l) {
        this.lessons.add(l);
    }

    // === END OF LESSON MANAGEMENT

    // === COMPLAINT MANAGER ===

    public void addComplaint(Complaint c) { this.complaints.add(c); }

    public List<Complaint> getComplaints() { return complaints; }

    public List<Complaint> getComplaintsBySender(Employee e) {
        return DataBase.getInstance().getComplaints().stream()
                .filter(complaint -> complaint.getSender().equals(e))
                .collect(Collectors.toList());
    }

    // === END OF COMPLAINT MANAGER ===

    // === NEWS MANAGEMENT ===

    public List<News> getNews() {
        return news;
    }

    public void addNews(News n) {
        this.news.add(n);
    }

    // === END OF NEWS MANAGEMENT ===

    // === TEACHER MANAGEMENT ===

    public List<Course> getTeacherCourses(Teacher t) {
        return DataBase.getInstance().getCourses().stream()
                .filter(course -> course.getTeachers().contains(t))
                .collect(Collectors.toList());
    }

    // === END OF TEACHER MANAGEMENT

    // === TICKET MANAGEMENT ===

    public List<Ticket> getTickets() {
        return this.tickets;
    }

    // === END OF TICKET MANAGEMENT
}
