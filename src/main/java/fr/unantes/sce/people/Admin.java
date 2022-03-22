package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;
import fr.unantes.sce.exception.InvalidRoleException;
import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.wrapper.NullableMonoValuedAttribute;

public class Admin extends Role {

    public static final Role INSTANCE = new Admin();

    /**
     * Creates a new admin
     */
    private Admin() {
        super();
    }

    /**
     * {@Inherited}
     */
    @Override
    public NullableMonoValuedAttribute<Calendar> calendar() throws InvalidRoleException {
        throw new InvalidRoleException("Invalid operation. Only agent have a calendar!");
    }

    /**
     * {@Inherited}
     */
    @Override
    public void addTravelTo(Travel travel, Person agent) throws InvalidRoleException, MaximumSizeReachedException {
        agent.calendar().get().addTravel(travel);
    }

}