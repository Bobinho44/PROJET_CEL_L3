package fr.unantes.sce.calendar;

import fr.unantes.sce.exception.InconsistentArgumentException;
import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.exception.MinimumSizeReachedException;
import fr.unantes.sce.wrapper.BothBoundedMultiValuedAttribute;
import fr.unantes.sce.wrapper.NullableMonoValuedAttribute;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Travel stores a list of correspondence to be followed over time, associated with a calendar
 */
public class Travel {

    /**
     * The maximum number of steps that a travel can contain
     */
    private static final int MAX_STEPS_NUMBER_IN_TRAVEL = 10;

    /**
     * The minimum number of steps that a travel can contain
     */
    private static final int MIN_STEPS_NUMBER_IN_TRAVEL = 1;

    /**
     * Fields
     */
    private final BothBoundedMultiValuedAttribute<Correspondence> steps = new BothBoundedMultiValuedAttribute<>(MIN_STEPS_NUMBER_IN_TRAVEL, MAX_STEPS_NUMBER_IN_TRAVEL);
    private final NullableMonoValuedAttribute<Calendar> parent = new NullableMonoValuedAttribute<>();

    /**
     * Creates a new travel
     *
     * @param parent the parent associated to the travel
     */
    public Travel(@Nonnull Calendar parent) {
        this.setParent(parent);
    }

    /**
     * Gets the steps wrapper
     *
     * @return the steps wrapper
     */
    @Nonnull
    protected BothBoundedMultiValuedAttribute<Correspondence> steps() {
        return steps;
    }

    /**
     * Gets the parent wrapper
     *
     * @return the parent wrapper
     */
    @Nonnull
    protected NullableMonoValuedAttribute<Calendar> parent() {
        return parent;
    }

    /**
     * Gets the first travel step
     *
     * @return the first travel step
     */
    @Nonnull
    public Correspondence getFirstStep() {
        return steps().get(0);
    }

    /**
     * Gets the last travel step
     *
     * @return the last travel step
     */
    @Nonnull
    public Correspondence getLastStep() {
        return steps().get(steps().size() - 1);
    }

    /**
     * Adds the step to the travel
     *
     * @param step the added step
     * @throws MaximumSizeReachedException   if the travel list is full
     * @throws InconsistentArgumentException if the step beginning starts before the end of the last current step
     */
    public void addStep(@Nonnull Correspondence step) throws MaximumSizeReachedException, InconsistentArgumentException {
        if (isTheNewStepInconsistent(step)) {
            throw new InconsistentArgumentException("Invalid operation. The step is inconsistent with the current steps of the travel!");
        }

        if (stepIsAlreadyLinkedWithATravel(step)) {
            step.travel().get().steps().basicRemove(step);
        }

        steps().add(step);
        step.travel().set(this);
    }

    /**
     * Removes the step from the travel
     *
     * @param step the removed step
     * @throws MinimumSizeReachedException if the travel list has reached its minimum number of steps
     */
    public void removeStep(@Nonnull Correspondence step) throws MinimumSizeReachedException {
        steps().remove(step);
        step.travel().unset();
    }

    /**
     * Checks if the step beginning starts before the end of the last current step
     *
     * @param step the checked step
     * @return true if the step beginning starts before the end of the last current step, false otherwise
     */
    private boolean isTheNewStepInconsistent(@Nonnull Correspondence step) {
        return !steps().isEmpty() && getLastStep().timeInterval().isEndedAfterItsBegin(step.timeInterval());
    }

    /**
     * Checks if the step is already linked with another travel
     *
     * @param step the checked step
     * @return true if the step is already linked with another travel, false otherwise
     */
    private boolean stepIsAlreadyLinkedWithATravel(@Nonnull Correspondence step) {
        return step.travel().get() != null;
    }

    /**
     * Sets the new travel parent
     *
     * @param parent the new travel parent
     */
    public void setParent(@Nonnull Calendar parent) {
        if (isAlreadyLinkedWithACalendar()) {
            parent().get().travels().basicRemove(this);
        }

        parent().set(parent);
        parent.travels().add(this);
    }

    /**
     * Checks if the travel is already linked with another calendar
     *
     * @return true if the travel is already linked with another calendar, false otherwise
     */
    private boolean isAlreadyLinkedWithACalendar() {
        return parent().get() != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travel travel = (Travel) o;
        return Objects.equals(steps(), travel.steps()) &&
                Objects.equals(parent().get(), travel.parent().get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(steps().get(), parent().get());
    }

}