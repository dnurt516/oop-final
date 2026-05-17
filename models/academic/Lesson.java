package models.academic;

import enums.DayOfWeek;
import models.users.Teacher;
import enums.LessonType;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Lesson implements Serializable {
    @Serial
    private static final long serialVersionUID = 2108044861129585493L;

    private LessonType type;
    private DayOfWeek dayOfWeek;
    private String time;
    private String room;
    private Course course;
    private Teacher teacher;

    public Lesson() {
    }

    public Lesson(Course course, Teacher teacher, LessonType type, DayOfWeek day, String time, String room) {
        this.course = course;
        this.teacher = teacher;
        this.type = type;
        this.dayOfWeek = day;
        this.time = time;
        this.room = room;
    }

    public LessonType getType() { return type; }
    public void setType(LessonType type) { this.type = type; }

    public DayOfWeek getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(DayOfWeek dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public String getTime() { return time; }
    public void setTime(String timeStr) {
        try {
            this.time = LocalTime.parse(timeStr).toString();
        } catch (DateTimeParseException e) {
            System.err.println("Invalid time format! Use HH:mm (00:00 - 23:59)");
        }
    }

    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    @Override
    public String toString() {
        String day = (dayOfWeek != null) ? dayOfWeek.toString() : "TBD";
        String formattedTime = (time != null) ? time : "TBD";

        return String.format("[%s %s] %s | %s | Room: %s | Teacher: %s",
                day,
                formattedTime,
                (course != null ? course.getName() : "Unknown Course"),
                (type != null ? type : "Lesson"),
                (room != null ? room : "TBD"),
                (teacher != null ? teacher.getLastName() : "Staff")
        );
    }
}