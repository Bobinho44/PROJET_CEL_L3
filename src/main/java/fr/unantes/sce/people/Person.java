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

    private final MonoValuedAttribute<String> name;
    private final MonoValuedAttribute<Role> role;
    private final NullableMonoValuedAttribute<Calendar> calendar = new NullableMonoValuedAttribute<>();

    /**
     * Creates a new person
     *
     * @param name the name
     * @param role the role
     */
    public Person(String name, Role role) {
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

    public NullableMonoValuedAttribute<Calendar> calendar() throws InvalidRoleException {
        return role().get().calendar(this);
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
        role().get().addTravelTo(travel, agent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name().get(), person.name().get()) &&
                Objects.equals(role().get(), person.role().get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name().get(), role().get());
    }

}