package fr.unantes.sce.calendar;

import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.people.Person;
import fr.unantes.sce.wrapper.NullableMonoValuedAttribute;
import fr.unantes.sce.wrapper.UpperBoundedMultiValuedAttribute;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * A Calendar stores a list of travels for an agent
 */
public class Calendar {

    /**
     * The maximum number of trips that a calendar can contain
     */
    private static final int MAX_TRAVEL_NUMBER_IN_CALENDAR = 10;

    /**
     * Fields
     */
    private final UpperBoundedMultiValuedAttribute<Travel> travels = new UpperBoundedMultiValuedAttribute<>(MAX_TRAVEL_NUMBER_IN_CALENDAR);
    private final NullableMonoValuedAttribute<Person> owner = new NullableMonoValuedAttribute<>();

    /**
     * Creates a new calendar
     *
     * @param owner the owner associated to the calendar
     */
    public Calendar(@Nonnull Person owner) {
        setOwner(owner);
    }

    /**
     * Gets the travels wrapper
     *
     * @return the travels wrapper
     */
    @Nonnull
    protected UpperBoundedMultiValuedAttribute<Travel> travels() {
        return travels;
    }

    /**
     * Gets the owner wrapper
     *
     * @return the owner wrapper
     */
    @Nonnull
    public NullableMonoValuedAttribute<Person> owner() {
        return owner;
    }

    /**
     * Adds the travel to the calendar
     *
     * @param travel the added travel
     * @throws MaximumSizeReachedException if the travel list is full
     */
    public void addTravel(@Nonnull Travel travel) throws MaximumSizeReachedException {
        if (travelIsAlreadyLinkedWithACalendar(travel)) {
            travel.parent().get().travels().basicRemove(travel);
        }

        this.travels.add(travel);
        travel.parent().set(this);
    }

    /**
     * Removes the travel from the calendar
     *
     * @param travel the removed travel
     */
    public void removeTravel(@Nonnull Travel travel) {
        this.travels.remove(travel);
        travel.parent().unset();
    }

    /**
     * Checks if the calendar contain the travel
     *
     * @param travel the checked travel
     * @return true if the calendar contain the travel, false otherwise
     */
    public boolean containTravel(@Nonnull Travel travel) {
        return this.travels.contain(travel);
    }

    /**
     * Checks if the travel is already linked with another calendar
     *
     * @param travel the checked travel
     * @return true if the travel is already linked with another calendar, false otherwise
     */
    private boolean travelIsAlreadyLinkedWithACalendar(@Nonnull Travel travel) {
        return Objects.nonNull(travel.parent().get());
    }

    /**
     * Sets the new calendar owner
     *
     * @param owner the new calendar owner
     */
    public void setOwner(@Nonnull Person owner) {
        if (ownerIsAlreadyLinkedWithACalendar(owner)) {
            owner.calendar().get().owner().unset();
        }

        this.owner.set(owner);
        owner.calendar().set(this);
    }

    /**
     * Checks if the owner is already linked with another calendar
     *
     * @param owner the checked owner
     * @return true the owner is already linked with another calendar, false otherwise
     */
    private boolean ownerIsAlreadyLinkedWithACalendar(@Nonnull Person owner) {
        return Objects.nonNull(owner.calendar().get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar calendar = (Calendar) o;
        return Objects.equals(travels, calendar.travels()) &&
                Objects.equals(owner.get(), calendar.owner().get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(travels, owner.get());
    }

}