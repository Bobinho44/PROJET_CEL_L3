package fr.unantes.sce.calendar;

import fr.unantes.sce.people.Agent;
import fr.unantes.sce.people.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CalendarTest {

    private Calendar calendar;
    private Travel travel1;
    private Travel travel2;
    private final List<Travel> travels = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Person person = new Person("Kylian", Agent.INSTANCE);
        Person person2 = new Person("Alix", Agent.INSTANCE);
        Calendar calendar2 = new Calendar(person2);

        calendar = new Calendar(person);
        travel1 = new Travel(calendar);
        travel2 = new Travel(calendar2);
        for (int i = 0; i < 10; i++) {
            travels.add(new Travel(new Calendar(person2)));
        }
    }

    @Test
    void addTravel_AdditionDone_true() {
        Assertions.assertEquals(1, calendar.travels().size());

        calendar.addTravel(travel2);

        Assertions.assertEquals(travel2, calendar.travels().get(1));
    }

    @Test
    void removeTravel_DeletionDone_true() {
        Assertions.assertEquals(1, calendar.travels().size());

        calendar.removeTravel(travel1);

        Assertions.assertEquals(0, calendar.travels().size());
    }

    @Test
    void addTravel_Add11Travels_ExceptionThrown() {
        Exception exception = Assertions.assertThrows(
                Exception.class,
                () -> {
                    for (Travel travel : travels) {
                        calendar.addTravel(travel);
                    }
                }
        );

        Assertions.assertEquals("Invalid operation. The multiValuedAttribute is already full!", exception.getMessage());
    }

    @Test
    void addTravel_TravelParentIsSet_true() {
        Assertions.assertNotEquals(calendar, travel2.parent().get());

        calendar.addTravel(travel2);

        Assertions.assertEquals(calendar, travel2.parent().get());
    }

    @Test
    void removeTravel_TravelParentIsUnSet_true() {
        calendar.removeTravel(travel1);

        Assertions.assertNull(travel1.parent().get());
    }

    @Test
    void getTravel_GettingDone_true() {
        Assertions.assertEquals(1, calendar.travels().size());

        for (int i = 0; i < 7; i++) {
            Travel travel2 = new Travel(calendar);
            calendar.addTravel(travel2);
        }

        Assertions.assertEquals(8, calendar.travels().size());
    }

    @Test
    void removeTravel_TravelIsNotLinked_ExceptionThrown() {
        Exception exception = Assertions.assertThrows(
                Exception.class,
                () -> calendar.removeTravel(travel2)
        );

        Assertions.assertEquals("Invalid operation. The multiValuedAttribute does not contain this value!", exception.getMessage());
    }

}