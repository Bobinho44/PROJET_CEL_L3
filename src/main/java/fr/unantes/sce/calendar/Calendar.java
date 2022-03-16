package fr.unantes.sce.calendar;

import fr.unantes.sce.people.Person;
import fr.unantes.sce.wrapper.UpperBoundedMultiValuedReference;

/**
 * A Calendar stores a list of travels for an agent
 */
public class Calendar {

    private static final int MAX_TRAVEL_NUMBER_IN_CALENDAR = 10;

    private final UpperBoundedMultiValuedReference<Travel> travels = new UpperBoundedMultiValuedReference<>(MAX_TRAVEL_NUMBER_IN_CALENDAR);
    private Person owner;

    public Calendar(Person owner) {
        this.owner = owner;
    }

    public UpperBoundedMultiValuedReference<Travel> travels() {
        return travels;
    }

    public void addTravel(Travel travel) {
        travel.parent().get().removeTravel(travel);
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