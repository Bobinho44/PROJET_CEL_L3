package fr.unantes.sce.calendar;

import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.exception.MinimumSizeReachedException;
import fr.unantes.sce.wrapper.BothBoundedMultiValuedAttribute;
import fr.unantes.sce.wrapper.NullableMonoValuedAttribute;

import java.util.Objects;

/**
 * Travel goes from one place to another, with a departure date and an arrival date
 */
public class Travel {

    private static final int MAX_STEPS_NUMBER_IN_TRAVEL = 10;
    private static final int MIN_STEPS_NUMBER_IN_TRAVEL = 1;

    private final BothBoundedMultiValuedAttribute<Correspondence> steps = new BothBoundedMultiValuedAttribute<>(MIN_STEPS_NUMBER_IN_TRAVEL, MAX_STEPS_NUMBER_IN_TRAVEL);
    private final NullableMonoValuedAttribute<Calendar> parent = new NullableMonoValuedAttribute<>();

    public Travel(Calendar parent) {
        this.setParent(parent);
    }

    protected BothBoundedMultiValuedAttribute<Correspondence> steps() {
        return steps;
    }

    protected NullableMonoValuedAttribute<Calendar> parent() {
        return parent;
    }

    public Correspondence getFirstStep() {
        return steps().get(0);
    }

    public Correspondence getLastStep() {
        return steps().get(steps().size() - 1);
    }

    public void addStep(Correspondence step) throws MaximumSizeReachedException {
        if (stepIsAlreadyLinkedWithATravel(step)) {
            step.travel().get().steps().basicRemove(step);
        }

        steps().add(step);
        step.travel().set(this);
    }

    public void removeStep(Correspondence step) throws MinimumSizeReachedException {
        steps().remove(step);
        step.travel().unset();
    }

    private boolean stepIsAlreadyLinkedWithATravel(Correspondence step) {
        return step.travel().get() != null;
    }

    public void setParent(Calendar parent) {
        if (isAlreadyLinkedWithACalendar()) {
            parent().get().travels().basicRemove(this);
        }

        parent().set(parent);
        parent.travels().add(this);
    }

    private boolean isAlreadyLinkedWithACalendar() {
        return parent().get() != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travel travel = (Travel) o;
        return Objects.equals(steps(), travel.steps()) &&
                Objects.equals(parent().get(), travel.parent().get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(steps().get(), parent().get());
    }

}