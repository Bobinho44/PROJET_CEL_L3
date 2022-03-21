package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;
import fr.unantes.sce.exception.InvalidRoleException;
import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.wrapper.NonNullMonoValuedAttribute;
import fr.unantes.sce.wrapper.NullableMonoValuedAttribute;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * A Generic person, which can be an agent or an administrator
 */
public class Person {

    private final NonNullMonoValuedAttribute<String> name;
    private final NonNullMonoValuedAttribute<Role> role;

    /**
     * Creates a new person
     *
     * @param name the name
     * @param role the role
     */
    public Person(String name, Role role) {
        this.name = new NonNullMonoValuedAttribute<>(name);
        this.role = new NonNullMonoValuedAttribute<>(role);
    }

    /**
     * Gets the name wrapper
     *
     * @return the name wrapper
     */
    @Nonnull
    public NonNullMonoValuedAttribute<String> name() {
        return name;
    }

    /**
     * Gets the role wrapper
     *
     * @return the role wrapper
     */
    @Nonnull
    public NonNullMonoValuedAttribute<Role> role() {
        return role;
    }

    /**
     * Gets the calendar wrapper
     *
     * @return the calendar wrapper
     */
    @Nonnull
    public NullableMonoValuedAttribute<Calendar> calendar() throws InvalidRoleException {
        return role.get().calendar();
    }

    /**
     * Adds travel to an agent
     *
     * @param travel the travel
     * @param agent  the agent
     * @throws InvalidRoleException        the person is not an admin
     * @throws MaximumSizeReachedException the calendar of this agent is already full
     */
    public void addTravelTo(Travel travel, Person agent) throws InvalidRoleException, MaximumSizeReachedException {
        role.get().addTravelTo(travel, agent);
    }

    /**
     * {@Inherited}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(role, person.role);
    }

    /**
     * {@Inherited}
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, role);
    }

}