package fr.unantes.sce.calendar;

import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.people.Person;
import fr.unantes.sce.wrapper.UpperBoundedMultiValuedAttribute;

import java.util.Objects;

/**
 * A Calendar stores a list of travels for an agent
 */
public class Calendar {

    private static final int MAX_TRAVEL_NUMBER_IN_CALENDAR = 10;

    private final UpperBoundedMultiValuedAttribute<Travel> travels = new UpperBoundedMultiValuedAttribute<>(MAX_TRAVEL_NUMBER_IN_CALENDAR);
    private Person owner;

    public Calendar(Person owner) {
        this.owner = owner;
    }

    protected UpperBoundedMultiValuedAttribute<Travel> travels() {
        return travels;
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
        return travel.parent().get() != null;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar calendar = (Calendar) o;
        return Objects.equals(travels(), calendar.travels()) &&
                Objects.equals(getOwner(), calendar.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(travels(), getOwner());
    }
}