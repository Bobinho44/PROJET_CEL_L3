package fr.unantes.sce.calendar;

import fr.unantes.sce.people.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class CorrespondenceTest {

    Correspondence correspondence;

    @BeforeEach
    void setUp() throws Exception {
        Person person = new Person("Kylian", "agent");
        Calendar calendar = new Calendar(person);
        Travel travel = new Travel(calendar);
        City city1 = new City("France", "Nantes");
        City city2 = new City("Corée du Sud", "Séoul");
        LocalDate date1 = LocalDate.of(2000, 2, 15);
        LocalDate date2 = LocalDate.of(2000, 2, 16);
        correspondence = new Correspondence(travel, city1, city2, date1, date2);
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
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> correspondence.setStartTime(LocalDate.of(2001, 2, 17))
        );

        Assertions.assertEquals("startTime is after this correspondence arrivalTime!", exception.getMessage());
    }

    @Test
    void setArrivalTime_BeforeStartTime_ExceptionThrown() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> correspondence.setArrivalTime(LocalDate.of(1999, 2, 17))
        );

        Assertions.assertEquals("arrivalTime is before this correspondence startTime!", exception.getMessage());
    }

    @Test
    void Correspondence_BeforeStartTime_ExceptionThrown() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Person person = new Person("Kylian", "agent");
                    Calendar calendar = new Calendar(person);
                    Travel travel = new Travel(calendar);
                    City city1 = new City("France", "Nantes");
                    City city2 = new City("Corée du Sud", "Séoul");
                    LocalDate date1 = LocalDate.of(2000, 2, 16);
                    LocalDate date2 = LocalDate.of(2000, 2, 15);
                    correspondence = new Correspondence(travel, city1, city2, date1, date2);
                }
        );

        Assertions.assertEquals("arrivalTime is before startTime!", exception.getMessage());
    }

}