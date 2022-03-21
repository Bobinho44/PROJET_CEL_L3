package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;
import fr.unantes.sce.exception.InvalidRoleException;
import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.wrapper.InitiallyEmptyMonoValuedAttribute;

public abstract class Role {

    protected final InitiallyEmptyMonoValuedAttribute<Calendar> calendar = new InitiallyEmptyMonoValuedAttribute<>();

    /**
     * Gets the calendar wrapper
     *
     * @return the calendar wrapper
     * @throws InvalidRoleException the role is not agent
     */
    public abstract InitiallyEmptyMonoValuedAttribute<Calendar> calendar() throws InvalidRoleException;

    /**
     * Adds travel to an agent
     *
     * @param travel the travel
     * @param agent  the agent
     * @throws InvalidRoleException        the person is not an admin
     * @throws MaximumSizeReachedException the calendar of this agent is already full
     */
    public abstract void addTravelTo(Travel travel, Person agent) throws InvalidRoleException, MaximumSizeReachedException;

}