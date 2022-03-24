package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;
import fr.unantes.sce.exception.InvalidRoleException;
import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.wrapper.NullableMonoValuedAttribute;

import javax.annotation.Nonnull;

/**
 * A Role represents the status of a person
 */
public interface Role {

    /**
     * Gets the calendar wrapper
     *
     * @return the calendar wrapper
     * @throws InvalidRoleException if the person is not an agent
     */
    @Nonnull
    NullableMonoValuedAttribute<Calendar> calendar(@Nonnull Person agent) throws InvalidRoleException;

    /**
     * Adds the travel to an agent calendar
     *
     * @param travel the added travel
     * @param agent  the affected agent
     * @throws InvalidRoleException        if the person is not an admin
     * @throws MaximumSizeReachedException if the calendar of the agent is already full
     */
    void addTravelTo(@Nonnull Travel travel, @Nonnull Person agent) throws InvalidRoleException, MaximumSizeReachedException;

}