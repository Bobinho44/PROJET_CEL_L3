package fr.unantes.sce.calendar;

import fr.unantes.sce.exception.InvalidArgumentException;
import fr.unantes.sce.people.Agent;
import fr.unantes.sce.people.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class CorrespondenceTest {

    private Correspondence correspondence;
    private Travel travel1;
    private Travel travel2;
    private City city1;
    private City city2;
    private LocalDate date1;
    private LocalDate date2;
    private LocalDate date3;
    private LocalDate date4;

    @BeforeEach
    void setUp() {
        Person person = new Person("Kylian", Agent.INSTANCE);

        travel1 = new Travel(new Calendar(person));
        travel2 = new Travel(new Calendar(person));
        city1 = new City("France", "Nantes");
        city2 = new City("Corée du Sud", "Séoul");
        date1 = LocalDate.of(2000, 2, 15);
        date2 = LocalDate.of(2000, 2, 16);
        date3 = LocalDate.of(2001, 2, 17);
        date4 = LocalDate.of(1999, 2, 17);
        correspondence = new Correspondence(travel1, city1, city2, date1, date2);
    }

    @Test
    void getStartTime_GettingDone_True() {
        Assertions.assertEquals(LocalDate.of(2000, 2, 15), correspondence.getStartTime());
    }

    @Test
    void getArrivalTime_GettingDone_True() {
        Assertions.assertEquals(LocalDate.of(2000, 2, 16), correspondence.getArrivalTime());
    }

    @Test
    void setStartTime_SettingDone_True() {
        Assertions.assertNotEquals(LocalDate.of(2000, 2, 14), correspondence.getStartTime());

        correspondence.setStartTime(LocalDate.of(2000, 2, 14));

        Assertions.assertEquals(LocalDate.of(2000, 2, 14), correspondence.getStartTime());
        Assertions.assertEquals(LocalDate.of(2000, 2, 16), correspondence.getArrivalTime());
    }

    @Test
    void setArrivalTime_SettingDone_True() {
        correspondence.setArrivalTime(LocalDate.of(2000, 2, 17));

        Assertions.assertEquals(LocalDate.of(2000, 2, 15), correspondence.getStartTime());
        Assertions.assertEquals(LocalDate.of(2000, 2, 17), correspondence.getArrivalTime());
    }

    @Test
    void setStartTime_AfterArrivalTime_ExceptionThrown() {
        InvalidArgumentException exception = Assertions.assertThrows(
                InvalidArgumentException.class,
                () -> correspondence.setStartTime(date3)
        );

        Assertions.assertEquals("Invalid argument. StartTime is after arrivalTime!", exception.getMessage());
    }

    @Test
    void setArrivalTime_BeforeStartTime_ExceptionThrown() {
        InvalidArgumentException exception = Assertions.assertThrows(
                InvalidArgumentException.class,
                () -> correspondence.setArrivalTime(date4)
        );

        Assertions.assertEquals("Invalid argument. ArrivalTime is before startTime!", exception.getMessage());
    }

    @Test
    void Correspondence_BeforeStartTime_ExceptionThrown() {
        InvalidArgumentException exception = Assertions.assertThrows(
                InvalidArgumentException.class,
                () -> correspondence = new Correspondence(travel1, city1, city2, date2, date1)
        );

        Assertions.assertEquals("arrivalTime is before startTime!", exception.getMessage());
    }

    @Test
    void setTravel_BidirectionalAssociationDone_True() {
        Assertions.assertFalse(travel2.steps().contain(correspondence));

        correspondence.setTravel(travel2);

        Assertions.assertTrue(travel2.steps().contain(correspondence));
    }

    @Test
    void getTravel_GettingDone_True() {
        Assertions.assertEquals(travel1, correspondence.travel().get());
    }

}