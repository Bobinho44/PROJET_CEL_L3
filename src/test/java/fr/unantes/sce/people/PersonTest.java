package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonTest {

    private Person agent1;
    private Person agent2;
    private Person admin;
    private Travel travel1;

    @BeforeEach
    void setUp() {
        agent1 = new Person("Cobaye1", Agent.INSTANCE);
        agent2 = new Person("Kylian", Agent.INSTANCE);
        admin = new Person("Cobaye2", Admin.INSTANCE);
        travel1 = new Travel(new Calendar(new Person("Cobaye3", Agent.INSTANCE)));
    }

    @Test
    void setName_SettingDone_True() {
        Assertions.assertNotEquals("Alix", agent1.name().get());

        agent1.name().set("Alix");

        Assertions.assertEquals("Alix", agent1.name().get());
    }

    @Test
    void setRole_PersonIsAgent_True() {
        Assertions.assertNotEquals(Admin.INSTANCE, agent1.role().get());

        agent1.role().set(Admin.INSTANCE);

        Assertions.assertEquals(Admin.INSTANCE, agent1.role().get());
    }

    @Test
    void setRole_PersonIsAdmin_True() {
        Assertions.assertNotEquals(Agent.INSTANCE, admin.role().get());

        admin.role().set(Agent.INSTANCE);

        Assertions.assertEquals(Agent.INSTANCE, admin.role().get());
    }

    @Test
    void setCalendar_PersonIsAgent_True() {
        Assertions.assertNull(agent1.calendar().get());

        agent1.calendar().set(new Calendar(agent1));

        Assertions.assertEquals(agent1, agent1.calendar().get().owner().get());
    }

    @Test
    void addTravelTo_PersonIsAdmin_True() {
        Assertions.assertNull(agent1.calendar().get());

        admin.addTravelTo(travel1, agent2);

        Assertions.assertTrue(agent2.calendar().get().containTravel(travel1));
    }

    @Test
    void useCalendar_PersonIsAnAdmin_ExceptionThrown() {
        Exception exception = Assertions.assertThrows(
                Exception.class,
                () -> admin.calendar()
        );

        Assertions.assertEquals("Invalid operation. Only agent have a calendar!", exception.getMessage());
    }

    @Test
    void addTravelTo_PersonIsAnAgent_ExceptionThrown() {
        Exception exception = Assertions.assertThrows(
                Exception.class,
                () -> agent1.addTravelTo(travel1, agent2)
        );

        Assertions.assertEquals("Invalid operation. Only an admin can add travel to an agent!", exception.getMessage());
    }

    @Test
    void setCalendar_PersonsAreAgent_True() {
        Assertions.assertNull(agent1.calendar().get());
        Assertions.assertNull(agent2.calendar().get());

        agent1.calendar().set(new Calendar(agent1));
        agent2.calendar().set(new Calendar(agent2));

        Assertions.assertEquals(agent1, agent1.calendar().get().owner().get());
        Assertions.assertEquals(agent2, agent2.calendar().get().owner().get());
    }

}