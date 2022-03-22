package fr.unantes.sce.calendar;

import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.people.Person;
import fr.unantes.sce.wrapper.NullableMonoValuedAttribute;
import fr.unantes.sce.wrapper.UpperBoundedMultiValuedAttribute;

import java.util.Objects;

/**
 * A Calendar stores a list of travels for an agent
 */
public class Calendar {

    private static final int MAX_TRAVEL_NUMBER_IN_CALENDAR = 10;

    private final UpperBoundedMultiValuedAttribute<Travel> travels = new UpperBoundedMultiValuedAttribute<>(MAX_TRAVEL_NUMBER_IN_CALENDAR);
    private final NullableMonoValuedAttribute<Person> owner = new NullableMonoValuedAttribute<>();

    public Calendar(Person owner) {
        setOwner(owner);
    }

    protected UpperBoundedMultiValuedAttribute<Travel> travels() {
        return travels;
    }

    public NullableMonoValuedAttribute<Person> owner() {
        return owner;
    }

    public void addTravel(Travel travel) throws MaximumSizeReachedException {
        if (travelIsAlreadyLinkedWithACalendar(travel)) {
            travel.parent().get().travels().basicRemove(travel);
        }

        travels().add(travel);
        travel.parent().set(this);
    }

    public void removeTravel(Travel travel) {
        travels().remove(travel);
        travel.parent().unset();
    }

    public boolean containTravel(Travel travel) {
        return travels().contain(travel);
    }

    private boolean travelIsAlreadyLinkedWithACalendar(Travel travel) {
        return Objects.nonNull(travel.parent().get());
    }

    public void setOwner(Person owner) {
        if (ownerIsAlreadyLinkedWithACalendar(owner)) {
            owner.calendar().get().owner().unset();
        }

        owner().set(owner);
        owner.calendar().set(this);
    }

    private boolean ownerIsAlreadyLinkedWithACalendar(Person owner) {
        return Objects.nonNull(owner.calendar().get());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar calendar = (Calendar) o;
        return Objects.equals(travels(), calendar.travels()) &&
                Objects.equals(owner().get(), calendar.owner().get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(travels().get(), owner().get());
    }

}