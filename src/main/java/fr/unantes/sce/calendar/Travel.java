package fr.unantes.sce.calendar;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * A Travel goes from one place to another, with a departure date and an arrival date
 */
public class Travel {
    private List<Correspondence> steps = new ArrayList<>();
    private Calendar parent;

    public Travel(Calendar parent) {
        this.parent = parent;
    }

    public Calendar getParent() {
        return parent;
    }

    public void setParent(Calendar parent) {
        this.parent = parent;
    }

    public List<Correspondence> getSteps() {
        return steps;
    }

    public Correspondence getFirstStep() {
        return (Correspondence) steps.get(0);
    }

    public Correspondence getLastStep() {
        return (Correspondence) steps.get(steps.size() - 1);
    }

    public boolean addCorrespondence(Correspondence step) {
        return steps.add(step);
    }

    public boolean removeCorrespondence(Correspondence step) {
        return steps.remove(step);
    }
}
