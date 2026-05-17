package models.research;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResearcherBase implements Researcher, Serializable {
    @Serial
    private static final long serialVersionUID = 306757063913558655L;

    private List<ResearchPaper> papers = new ArrayList<>();
    private List<ResearchProject> projects = new ArrayList<>();

    @Override
    public int getHIndex() {
        if (papers.isEmpty()) return 0;

        List<Integer> citations = papers.stream()
                .map(ResearchPaper::getCitations)
                .sorted(Comparator.reverseOrder())
                .toList();

        int h = 0;
        for (int i = 0; i < citations.size(); i++) {
            if (citations.get(i) >= i + 1) h = i + 1;
            else break;
        }
        return h;
    }

    @Override
    public void addPaper(ResearchPaper p) {
        if (!papers.contains(p)) papers.add(p);
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        List<ResearchPaper> sorted = new ArrayList<>(papers);
        sorted.sort(c);
        sorted.forEach(System.out::println);
    }

    @Override
    public List<ResearchProject> getProjects() { return projects; }

    @Override
    public List<ResearchPaper> getPapers() { return papers; }
}
