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

        Assertions.assertEquals(travel, calendar.travels().get(0));
    }

    @Test
    void removeTravel_DeletionDone_true() {
        calendar.removeTravel(travel);

        Assertions.assertEquals(0, calendar.travels().size());
    }

    @Test
    void addTravel_Add11Travels_ExceptionThrown() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    for (int i = 0; i < 10; i++) {
                        Travel travel2 = new Travel(calendar);
                        calendar.addTravel(travel2);
                    }
                }
        );

        Assertions.assertEquals("values is already full!", exception.getMessage());
    }

    @Test
    void addTravel_TravelParentIsSet_true() {
        calendar.addTravel(travel);

        Assertions.assertEquals(calendar, travel.parent().get());
    }

    @Test
    void removeTravel_TravelParentIsUnSet_true() {
        calendar.removeTravel(travel);

        Assertions.assertNull(travel.parent().get());
    }

    @Test
    void getTravel_GettingDone_true() {
        for (int i = 0; i < 7; i++) {
            Travel travel2 = new Travel(calendar);
            calendar.addTravel(travel2);
        }

        Assertions.assertEquals(8, calendar.travels().get().size());
    }

    @Test
    void removeTravel_TravelIsNotLinked_ExceptionThrown() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Person person = new Person("Alix", "agent");
                    Calendar calendar2 = new Calendar(person);
                    Travel travel2 = new Travel(calendar2);
                    calendar.removeTravel(travel2);
                }
        );

        Assertions.assertEquals("this value is not linked to values!", exception.getMessage());
    }
}