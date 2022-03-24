package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;
import fr.unantes.sce.exception.InvalidRoleException;
import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.wrapper.MonoValuedAttribute;
import fr.unantes.sce.wrapper.NullableMonoValuedAttribute;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * A Generic person, which can be an agent or an administrator
 */
public class Person {

    /**
     * Fields
     */
    private final MonoValuedAttribute<String> name;
    private final MonoValuedAttribute<Role> role;
    private final NullableMonoValuedAttribute<Calendar> calendar = new NullableMonoValuedAttribute<>();

    /**
     * Creates a new person
     *
     * @param name the name associated to the person
     * @param role the role associated to the person
     */
    public Person(@Nonnull String name, @Nonnull Role role) {
        this.name = new MonoValuedAttribute<>(name);
        this.role = new MonoValuedAttribute<>(role);
    }

    /**
     * Gets the name wrapper
     *
     * @return the name wrapper
     */
    @Nonnull
    public MonoValuedAttribute<String> name() {
        return name;
    }

    /**
     * Gets the role wrapper
     *
     * @return the role wrapper
     */
    @Nonnull
    public MonoValuedAttribute<Role> role() {
        return role;
    }

    /**
     * Gets the calendar wrapper
     *
     * @return the calendar wrapper
     */
    @Nonnull
    protected NullableMonoValuedAttribute<Calendar> basicCalendar() {
        return calendar;
    }

    /**
     * Gets the calendar wrapper of an agent
     *
     * @return the calendar wrapper
     * @throws InvalidRoleException if the person is not an agent
     */
    @Nonnull
    public NullableMonoValuedAttribute<Calendar> calendar() throws InvalidRoleException {
        return role().get().calendar(this);
    }

    /**
     * Adds the travel to an agent
     *
     * @param travel the added travel
     * @param agent  the added agent
     * @throws InvalidRoleException        if the person is not an admin
     * @throws MaximumSizeReachedException if the calendar of this agent is already full
     */
    public void addTravelTo(@Nonnull Travel travel, @Nonnull Person agent) throws InvalidRoleException, MaximumSizeReachedException {
        role().get().addTravelTo(travel, agent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name().get(), person.name().get()) &&
                Objects.equals(role().get(), person.role().get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(name().get(), role().get());
    }

}