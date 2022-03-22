package fr.unantes.sce.calendar;

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
    private City city3;
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
        city3 = new City("France", "Clisson");
        date1 = LocalDate.of(2000, 2, 15);
        date2 = LocalDate.of(2000, 2, 16);
        date3 = LocalDate.of(2001, 2, 17);
        date4 = LocalDate.of(1999, 2, 17);
        correspondence = new Correspondence(travel1, city1, city2, date1, date2);
    }

    @Test
    void getStartTime_GettingDone_True() {
        Assertions.assertEquals(LocalDate.of(2000, 2, 15), correspondence.timeInterval().getBegin());
    }

    @Test
    void getArrivalTime_GettingDone_True() {
        Assertions.assertEquals(LocalDate.of(2000, 2, 16), correspondence.timeInterval().getEnd());
    }

    @Test
    void setStartTime_SettingDone_True() {
        Assertions.assertNotEquals(LocalDate.of(2000, 2, 14), correspondence.timeInterval().getBegin());

        correspondence.timeInterval().setBegin(LocalDate.of(2000, 2, 14));

        Assertions.assertEquals(LocalDate.of(2000, 2, 14), correspondence.timeInterval().getBegin());
        Assertions.assertEquals(LocalDate.of(2000, 2, 16), correspondence.timeInterval().getEnd());
    }

    @Test
    void setArrivalTime_SettingDone_True() {
        correspondence.timeInterval().setEnd(LocalDate.of(2000, 2, 17));

        Assertions.assertEquals(LocalDate.of(2000, 2, 15), correspondence.timeInterval().getBegin());
        Assertions.assertEquals(LocalDate.of(2000, 2, 17), correspondence.timeInterval().getEnd());
    }

    @Test
    void setStartTime_AfterArrivalTime_ExceptionThrown() {
        Exception exception = Assertions.assertThrows(
                Exception.class,
                () -> correspondence.timeInterval().setBegin(date3)
        );

        Assertions.assertEquals("Invalid operation. The begin is after the Interval end!", exception.getMessage());
    }

    @Test
    void setArrivalTime_BeforeStartTime_ExceptionThrown() {
        Exception exception = Assertions.assertThrows(
                Exception.class,
                () -> correspondence.timeInterval().setEnd(date4)
        );

        Assertions.assertEquals("Invalid operation. The end is before the Interval begin!", exception.getMessage());
    }

    @Test
    void Correspondence_BeforeStartTime_ExceptionThrown() {
        Exception exception = Assertions.assertThrows(
                Exception.class,
                () -> correspondence = new Correspondence(travel1, city1, city2, date2, date1)
        );

        Assertions.assertEquals("Invalid operation. The Interval begin is after the Interval end!", exception.getMessage());
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

    @Test
    void setOrigin_SettingDone_True() {
        Assertions.assertNotEquals(city3, correspondence.origin().get());

        correspondence.origin().set(city3);

        Assertions.assertEquals(city3, correspondence.origin().get());
    }

    @Test
    void setDestination_SettingDone_True() {
        Assertions.assertNotEquals(city3, correspondence.destination().get());

        correspondence.destination().set(city3);

        Assertions.assertEquals(city3, correspondence.destination().get());
    }

    @Test
    void setOrigin_CityIsNull_ExceptionThrown() {
        Exception exception = Assertions.assertThrows(
                Exception.class,
                () -> correspondence.origin().set(null)
        );

        Assertions.assertEquals("Invalid operation. The value is null!", exception.getMessage());
    }

    @Test
    void setDestination_CityIsNull_ExceptionThrown() {
        Exception exception = Assertions.assertThrows(
                Exception.class,
                () -> correspondence.destination().set(null)
        );

        Assertions.assertEquals("Invalid operation. The value is null!", exception.getMessage());
    }

}