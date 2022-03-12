package fr.unantes.sce.calendar;

import fr.unantes.sce.people.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalendarTest {

    private Calendar calendar;
    private Travel travel;

    @BeforeEach
    void setUp() throws Exception {
        Person person = new Person("Kylian", "agent");
        calendar = new Calendar(person);
        travel = new Travel(calendar);
    }

    @Test
    void addTravel_AdditionDone_true() {
        calendar.addTravel(travel);

        Assertions.assertEquals(travel, calendar.getTravels().get(0));
    }

    @Test
    void removeTravel_DeletionDone_true() {
        calendar.removeTravel(travel);

        Assertions.assertEquals(0, calendar.getTravels().size());
    }

}