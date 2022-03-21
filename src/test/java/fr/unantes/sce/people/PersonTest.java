package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;
import fr.unantes.sce.exception.InvalidRoleException;
import fr.unantes.sce.exception.MaximumSizeReachedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonTest {

    private Person agent;
    private Person admin;

    @BeforeEach
    void setUp() {
        agent = new Person("Cobaye1", Agent.INSTANCE);
        admin = new Person("Cobaye2", Admin.INSTANCE);
    }

    @Test
    void setName_SettingDone_True() {
        agent.name().set("Alix");

        Assertions.assertEquals("Alix", agent.name().get());
    }

    @Test
    void setRole_PersonIsAgent_True() {
        agent.role().set(Admin.INSTANCE);

        Assertions.assertEquals(Admin.INSTANCE, agent.role().get());
    }

    @Test
    void setRole_PersonIsAdmin_True() {
        admin.role().set(Agent.INSTANCE);

        Assertions.assertEquals(Agent.INSTANCE, admin.role().get());
    }

    @Test
    void getCalendar_PersonIsAgent_True() throws InvalidRoleException {
        agent.calendar().set(new Calendar(agent));

        Assertions.assertEquals(agent, agent.calendar().get().getOwner());
    }

    @Test
    void setCalendar_PersonIsAgent_True() throws InvalidRoleException {
        Calendar calendar = new Calendar(agent);
        agent.calendar().set(calendar);

        Assertions.assertEquals(calendar, agent.calendar().get());
    }

    @Test
    void addTravelTo_PersonIsAdmin_True() throws MaximumSizeReachedException, InvalidRoleException {
        Person agent2 = new Person("Cobaye2", Agent.INSTANCE);
        Travel travel = new Travel(new Calendar(agent2));
        agent.calendar().set(new Calendar(agent));
        admin.addTravelTo(travel, agent);

        Assertions.assertTrue(agent.calendar().get().travels().contain(travel));
    }

    @Test
    void getCalendar_PersonIsAnAdmin_ExceptionThrown() {
        InvalidRoleException exception = Assertions.assertThrows(
                InvalidRoleException.class,
                () -> admin.calendar().get()
        );

        Assertions.assertEquals("Invalid operation. Only agent have a calendar!", exception.getMessage());
    }

    @Test
    void setCalendar_PersonIsAnAdmin_ExceptionThrown() {
        InvalidRoleException exception = Assertions.assertThrows(
                InvalidRoleException.class,
                () -> {
                    Calendar calendar = new Calendar(agent);
                    admin.calendar().set(calendar);
                }
        );

        Assertions.assertEquals("Invalid operation. Only agent have a calendar!", exception.getMessage());
    }

    @Test
    void addTravelTo_PersonIsAnAgent_ExceptionThrown() {
        InvalidRoleException exception = Assertions.assertThrows(
                InvalidRoleException.class,
                () -> {
                    Person agent2 = new Person("Kylian", Agent.INSTANCE);
                    Calendar calendar = new Calendar(agent);
                    Travel travel = new Travel(calendar);
                    agent.addTravelTo(travel, agent2);
                }
        );

        Assertions.assertEquals("Invalid operation. Only an admin can add travel to an agent!", exception.getMessage());
    }

}
