package models.research;

import models.research.Researcher;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResearchPaper implements Comparable<ResearchPaper>, Serializable {
    @Serial
    private static final long serialVersionUID = -4547692167703284053L;

    private String title;
    private List<Researcher> authors = new ArrayList<>();
    private String journal;
    private String doi;
    private Date date;
    private int citations;
    private int pages;
    private String field;

    public ResearchPaper(String title, int citations, Date date) {
        this.title = title;
        this.citations = citations;
        this.date = date;
    }

    public int getCitations() { return citations; }
    public Date getDate() { return date; }

    @Override
    public int compareTo(ResearchPaper other) {
        if (this.citations != other.citations) {
            return Integer.compare(other.citations, this.citations);
        }

        return other.date.compareTo(this.date);
    }

    @Override
    public String toString() {
        return String.format("%s (Citations: %d, Date: %s)", title, citations, date);
    }
}