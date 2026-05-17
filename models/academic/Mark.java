package models.academic;

import models.users.Student;

import java.io.Serial;
import java.io.Serializable;

/**
 * 
 */
public class Mark implements Serializable {
    @Serial
    private static final long serialVersionUID = 4403272204887196535L;

    private double firstAttestation;
    private double secondAttestation;
    private double finalExam;
    private double total;
    private Course course;
    private Student student;

    public Mark() {
        this.firstAttestation = 0;
        this.secondAttestation = 0;
        this.finalExam = 0;
    }

    public Mark(double firstAttestation, double secondAttestation, double finalExam) {
        this.firstAttestation = firstAttestation;
        this.secondAttestation = secondAttestation;
        this.finalExam = finalExam;
    }

    public double getTotal() {
        return firstAttestation + secondAttestation + finalExam;
    }

    public double getGpaValue() {
        double total = getTotal();
        if (total >= 95) return 4.0;
        if (total >= 90) return 3.67;
        if (total >= 85) return 3.33;
        if (total >= 80) return 3.0;
        if (total >= 75) return 2.67;
        if (total >= 70) return 2.33;
        if (total >= 65) return 2.0;
        if (total >= 60) return 1.67;
        if (total >= 55) return 1.33;
        if (total >= 50) return 1.0;
        return 0.0;
    }

    public String getLetter() {
        double total = getTotal();
        if (total >= 95) return "A";
        if (total >= 90) return "A-";
        if (total >= 85) return "B+";
        if (total >= 80) return "B";
        if (total >= 75) return "B-";
        if (total >= 70) return "C+";
        if (total >= 65) return "C";
        if (total >= 60) return "C-";
        if (total >= 55) return "D+";
        if (total >= 50) return "D";
        if (total >= 30) return "FX";
        return "F";
    }

    public double getFirstAttestation() { return firstAttestation; }
    public double getSecondAttestation() { return secondAttestation; }
    public double getFinalExam() { return finalExam; }


    public void setFirstAttestation(double firstAttestation) { this.firstAttestation = firstAttestation; }
    public void setSecondAttestation(double secondAttestation) { this.secondAttestation = secondAttestation; }
    public void setFinalExam(double finalExam) { this.finalExam = finalExam; }

    @Override
    public String toString() {
        return String.format("Total: %.2f | Grade: %s | GPA: %.2f", getTotal(), getLetter(), getGpaValue());
    }
}