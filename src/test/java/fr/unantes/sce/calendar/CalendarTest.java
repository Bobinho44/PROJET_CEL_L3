package fr.unantes.sce.calendar;

import fr.unantes.sce.people.Person;
import org.atlanmod.testing.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalendarTest {

    private Calendar calendar;
    private Travel travel;

    @BeforeEach
    public void setUp() throws Exception {
        Person person = new Person("Kylian", "agent");
        calendar = new Calendar(person);
        travel = new Travel(calendar);
    }

    @Test
    void addTravel_AdditionDone_true() {
        calendar.addTravel(travel);

        Assertions.assertThat(calendar.getTravels().get(0).equals(calendar));
    }

    @Test
    void removeTravel_DeletionDone_true() {
        calendar.removeTravel(travel);

        Assertions.assertThat(calendar.getTravels().size() == 0);
    }

}