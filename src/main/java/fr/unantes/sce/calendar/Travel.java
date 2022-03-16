package fr.unantes.sce.calendar;

import fr.unantes.sce.wrapper.MonoValuedReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Travel goes from one place to another, with a departure date and an arrival date
 */
public class Travel {

    private List<Correspondence> steps = new ArrayList<>();
    private final MonoValuedReference<Calendar> parent = new MonoValuedReference<>();

    public Travel(Calendar parent) {
        setParent(parent);
    }

    public MonoValuedReference<Calendar> parent() {
        return parent;
    }

    public void setParent(Calendar parent) {
        if (parent().get() != null) {
            parent().get().removeTravel(this);
        }
        parent().set(parent);
        parent.travels().add(this);
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