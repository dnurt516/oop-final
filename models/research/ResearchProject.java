package models.research;

import exceptions.LowHIndexException;
import exceptions.NotResearcherException;
import models.users.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResearchProject implements Serializable {
    @Serial
    private static final long serialVersionUID = 3768871370883299041L;

    private String topic;
    private Researcher projectHead;
    private List<Researcher> participants = new ArrayList<>();
    private List<ResearchPaper> papers = new ArrayList<>();
    private Date startDate;

    public ResearchProject() {
        this.startDate = new Date();
    }

    public ResearchProject(String topic, Researcher projectHead) {
        this.topic = topic;
        this.projectHead = projectHead;
        this.startDate = new Date();

        this.participants.add(projectHead);
    }

    public void addParticipant(User u) {
        if (!(u instanceof Researcher r)) {
            throw new NotResearcherException(u.getEmail(), "Join Research Project");
        }

        if (r.getHIndex() < 2) {
            throw new LowHIndexException(r.getHIndex(), 2);
        }

        if (!participants.contains(r)) {
            participants.add(r);
        }
    }

    public void addPaper(ResearchPaper p) {
        if (p != null && !papers.contains(p)) {
            papers.add(p);
        }
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Researcher getProjectHead() {
        return projectHead;
    }

    public void setProjectHead(Researcher projectHead) {
        this.projectHead = projectHead;
    }

    public List<Researcher> getParticipants() {
        return participants;
    }

    public List<ResearchPaper> getPapers() {
        return papers;
    }

    public Date getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return String.format("Research Project: '%s' | Head: %s | Participants: %d | Papers: %d | Started: %s",
                topic,
                (projectHead != null ? projectHead.toString() : "None"),
                participants.size(),
                papers.size(),
                startDate);
    }
}