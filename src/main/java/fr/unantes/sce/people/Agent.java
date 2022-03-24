package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;
import fr.unantes.sce.exception.InvalidRoleException;
import fr.unantes.sce.wrapper.NullableMonoValuedAttribute;

import javax.annotation.Nonnull;

/**
 * An Agent is a person who have a calendar, but can't assign one to another person
 */
public class Agent implements Role {

    /**
     * Single instance of the agent role
     */
    public static final Role INSTANCE = new Agent();

    /**
     * Creates a new agent
     */
    private Agent() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public NullableMonoValuedAttribute<Calendar> calendar(@Nonnull Person agent) throws InvalidRoleException {
        return agent.basicCalendar();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTravelTo(@Nonnull Travel travel, @Nonnull Person admin) throws InvalidRoleException {
        throw new InvalidRoleException("Invalid operation. Only an admin can add travel to an agent!");
    }

}