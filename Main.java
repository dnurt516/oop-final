import data.UserRepository;
import enums.Role;
import enums.TeacherTitle;
import models.users.Admin;
import models.research.TeacherResearcher;
import models.users.Student;
import models.users.User;
import services.AuthService;
import data.DataBase;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        DataBase db = DataBase.getInstance();
//
//        Admin systemAdmin = new Admin("Daniyal", "Nurtaza", "d_nurtaza@kbtu.kz", "123456",  "SITE", 1000000.0);
//
//        db.getUsers().put(systemAdmin.getId(), systemAdmin);
//
//        System.out.println("--- Админ начинает работу ---");
//
//        User newProfessor = new TeacherResearcher(
//                "Asset",
//                "Abdirakhman",
//                "a_abdirakhman@kbtu.kz",
//                "123456",
//                "SITE",
//                100000,
//                TeacherTitle.PROFESSOR
//        );
//
//        systemAdmin.addUser(newProfessor);
//
//        User newStudent = new Student(
//                "Abdik",
//                "Nurzhan",
//                "a_abdik@kbtu.kz",
//                "123456",
//                2,
//                "SITE"
//        );
//
//        systemAdmin.addUser(newStudent);
//
//        System.out.println("\n--- Проверка безопасности в AuthService ---");
//
//        AuthService authService = new AuthService();
//
//        User loggedInUser = authService.login("a_abdirakhman@kbtu.kz", "123456");
//
//        if (loggedInUser != null) {
//            System.out.println("Хэш пароля в базе данных: " + loggedInUser.getPassword());
//        }
//
        UserRepository repo = new UserRepository();

        User u = repo.findByEmail("d_nurtaza@kbtu.kz");

        System.out.println(u.toString());
//
        db.saveToFile();
    }
}