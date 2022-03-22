package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;
import fr.unantes.sce.exception.InvalidRoleException;
import fr.unantes.sce.wrapper.NullableMonoValuedAttribute;

public class Agent implements Role {

    public static final Role INSTANCE = new Agent();

    /**
     * Creates a new agent
     */
    private Agent() {
        super();
    }

    /**
     * {@Inherited}
     */
    @Override
    public NullableMonoValuedAttribute<Calendar> calendar(Person agent) throws InvalidRoleException {
        return agent.basicCalendar();
    }

    /**
     * {@Inherited}
     */
    @Override
    public void addTravelTo(Travel travel, Person admin) throws InvalidRoleException {
        throw new InvalidRoleException("Invalid operation. Only an admin can add travel to an agent!");
    }

}