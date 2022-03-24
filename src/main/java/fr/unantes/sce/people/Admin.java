package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;
import fr.unantes.sce.exception.InvalidRoleException;
import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.wrapper.NullableMonoValuedAttribute;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * An Admin is a person who does not have a calendar, but can assign one to an agent
 */
public class Admin implements Role {

    /**
     * Single instance of the admin role
     */
    public static final Role INSTANCE = new Admin();

    /**
     * Creates a new admin
     */
    private Admin() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public NullableMonoValuedAttribute<Calendar> calendar(@Nonnull Person agent) throws InvalidRoleException {
        throw new InvalidRoleException("Invalid operation. Only agent have a calendar!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTravelTo(@Nonnull Travel travel, @Nonnull Person agent) throws InvalidRoleException, MaximumSizeReachedException {
        if (agentIsNotLinkedWithACalendar(agent)) {
            agent.basicCalendar().set(new Calendar(agent));
        }

        agent.calendar().get().addTravel(travel);
    }

    /**
     * Checks if the agent is not linked with another calendar
     *
     * @param agent the checked agent
     * @return true if the agent is not linked with another calendar, false otherwise
     */
    private boolean agentIsNotLinkedWithACalendar(@Nonnull Person agent) {
        return Objects.isNull(agent.basicCalendar().get());
    }

}