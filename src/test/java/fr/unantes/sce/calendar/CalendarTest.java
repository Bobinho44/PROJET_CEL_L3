package fr.unantes.sce.calendar;

import fr.unantes.sce.exception.InvalidArgumentException;
import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.people.Agent;
import fr.unantes.sce.people.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalendarTest {

    private Calendar calendar;
    private Travel travel1;
    private Travel travel2;

    @BeforeEach
    void setUp() throws MaximumSizeReachedException {
        Person person = new Person("Kylian", Agent.INSTANCE);
        calendar = new Calendar(person);
        travel1 = new Travel(calendar);
        Person person2 = new Person("Alix", Agent.INSTANCE);
        Calendar calendar2 = new Calendar(person2);
        travel2 = new Travel(calendar2);
    }

    @Test
    void addTravel_AdditionDone_true() throws MaximumSizeReachedException {
        calendar.addTravel(travel1);

        Assertions.assertEquals(travel1, calendar.travels().get(0));
    }

    @Test
    void removeTravel_DeletionDone_true() {
        calendar.removeTravel(travel1);

        Assertions.assertEquals(0, calendar.travels().size());
    }

    @Test
    void addTravel_Add11Travels_ExceptionThrown() {
        MaximumSizeReachedException exception = Assertions.assertThrows(
                MaximumSizeReachedException.class,
                () -> {
                    for (int i = 0; i < 10; i++) {
                        new Travel(calendar);
                    }
                }
        );

        Assertions.assertEquals("Invalid operation. The multiValuedAttribute is already full!", exception.getMessage());
    }

    @Test
    void addTravel_TravelParentIsSet_true() throws MaximumSizeReachedException {
        calendar.addTravel(travel1);

        Assertions.assertEquals(calendar, travel1.parent().get());
    }

    @Test
    void removeTravel_TravelParentIsUnSet_true() {
        calendar.removeTravel(travel1);

        Assertions.assertNull(travel1.parent().get());
    }

    @Test
    void getTravel_GettingDone_true() throws MaximumSizeReachedException {
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
                () -> calendar.removeTravel(travel2)
        );

        Assertions.assertEquals("Invalid operation. The multiValuedAttribute does not contain this value!", exception.getMessage());
    }

}