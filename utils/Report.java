package utils;

import models.academic.Course;
import models.academic.Mark;
import models.users.Student;
import data.DataBase;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Report implements Serializable {
    @Serial
    private static final long serialVersionUID = -2483860919881646060L;

    private Date generatedAt;
    private double averageGpa;
    private int totalStudents;
    private double failedStudents;
    private Map<Course, Double> averageMarkPerCourse = new HashMap<>();

    public Report() {
        this.generatedAt = new Date();
        calculateMetrics();
    }

    private void calculateMetrics() {
        List<Student> students = DataBase.getInstance().getAllUsersByType(Student.class);
        Map<Course, Integer> coursesMarksCount = new HashMap<>();

        this.totalStudents = students.size();

        if (totalStudents == 0) return;

        double sumGpa = 0;
        int failedCount = 0;

        for (Student s : students) {
            double  gpa = s.getGpa();
            sumGpa += gpa;

            if (gpa < 1.00) failedCount++;

            Map<Course, Mark> studentMarks = s.getTranscript();
            for (Map.Entry<Course, Mark> entry : studentMarks.entrySet()) {
                Course c = entry.getKey();
                Mark m = entry.getValue();

                Double currentSum = averageMarkPerCourse.getOrDefault(c, 0.0);

                averageMarkPerCourse.put(c, currentSum + m.getGpaValue());
                coursesMarksCount.put(c, coursesMarksCount.getOrDefault(c, 0) + 1);
            }
        }

        averageMarkPerCourse.replaceAll((k, v) -> v / coursesMarksCount.get(k));

        this.averageGpa = sumGpa / totalStudents;
        this.failedStudents = (double) failedCount / totalStudents;
    }
}