package fr.unantes.sce.calendar;

import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.wrapper.NullableMonoValuedAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Travel goes from one place to another, with a departure date and an arrival date
 */
public class Travel {

    private List<Correspondence> steps = new ArrayList<>();
    private final NullableMonoValuedAttribute<Calendar> parent = new NullableMonoValuedAttribute<>();

    public Travel(Calendar parent) throws MaximumSizeReachedException {
        setParent(parent);
    }

    public NullableMonoValuedAttribute<Calendar> parent() {
        return parent;
    }

    public void setParent(Calendar parent) throws MaximumSizeReachedException {
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