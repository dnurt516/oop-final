package models.research;

import data.DataBase;

import java.util.Comparator;
import java.util.List;

public interface Researcher {
    int getHIndex();
    void addPaper(ResearchPaper p);
    void printPapers(Comparator<ResearchPaper> c);
    List<ResearchProject> getProjects();
    List<ResearchPaper> getPapers();

    default void createResearchProject(String topic) {
        if (topic == null || topic.trim().isEmpty()) {
            System.out.println("Project topic cannot be empty!");
            return;
        }

        ResearchProject newProject = new ResearchProject(topic, this);

        DataBase.getInstance().getResearchProjects().add(newProject);
        System.out.println("Research Project '" + topic + "' successfully created!");
    }
}