package fr.unantes.sce.calendar;

import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.people.Person;
import fr.unantes.sce.wrapper.UpperBoundedMultiValuedAttribute;

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

    public UpperBoundedMultiValuedAttribute<Travel> travels() {
        return travels;
    }

    public void addTravel(Travel travel) throws MaximumSizeReachedException {
        if (travel.parent().get() != null) {
            travel.parent().get().removeTravel(travel);
        }
        travels().add(travel);
        travel.parent().set(this);
    }

    public void removeTravel(Travel travel) {
        travels().remove(travel);
        travel.parent().unset();
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

}