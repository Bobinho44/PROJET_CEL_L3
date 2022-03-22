package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;
import fr.unantes.sce.exception.InvalidRoleException;
import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.wrapper.NullableMonoValuedAttribute;

import java.util.Objects;

public class Admin implements Role {

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
    public NullableMonoValuedAttribute<Calendar> calendar(Person agent) throws InvalidRoleException {
        throw new InvalidRoleException("Invalid operation. Only agent have a calendar!");
    }

    /**
     * {@Inherited}
     */
    @Override
    public void addTravelTo(Travel travel, Person agent) throws InvalidRoleException, MaximumSizeReachedException {
        if (agentIsNotLinkedWithACalendar(agent)) {
            agent.basicCalendar().set(new Calendar(agent));
        }

        agent.calendar().get().addTravel(travel);
    }

    private boolean agentIsNotLinkedWithACalendar(Person agent) {
        return Objects.isNull(agent.basicCalendar().get());
    }

}