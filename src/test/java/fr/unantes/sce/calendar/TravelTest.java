package fr.unantes.sce.calendar;

import fr.unantes.sce.exception.InvalidArgumentException;
import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.people.Agent;
import fr.unantes.sce.people.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class TravelTest {

    private Travel travel;
    private Correspondence correspondence1;
    private Correspondence correspondence2;
    private Calendar calendar;

    @BeforeEach
    void setUp() throws InvalidArgumentException, MaximumSizeReachedException {
        Person person = new Person("Kylian", Agent.INSTANCE);
        calendar = new Calendar(person);
        travel = new Travel(calendar);
        City city1 = new City("France", "Nantes");
        City city2 = new City("Corée du Sud", "Séoul");
        LocalDate date1 = LocalDate.of(2000, 2, 15);
        LocalDate date2 = LocalDate.of(2000, 2, 16);
        correspondence1 = new Correspondence(travel, city1, city2, date1, date2);
        correspondence2 = new Correspondence(travel, city2, city1, date1, date2);
    }

    @Test
    void addStep_AdditionDone_true() {
        travel.getSteps().add(correspondence1);
        travel.getSteps().add(correspondence2);

        Assertions.assertEquals(correspondence1, travel.getFirstStep());
        Assertions.assertEquals(correspondence2, travel.getLastStep());
    }

    @Test
    void removeStep_DeletionDone_true() {
        travel.getSteps().add(correspondence1);
        travel.getSteps().add(correspondence2);

        Assertions.assertEquals(correspondence1, travel.getFirstStep());

        travel.getSteps().remove(correspondence1);

        Assertions.assertEquals(correspondence2, travel.getFirstStep());
    }

    @Test
    void setParent_SettingDone_true() throws MaximumSizeReachedException {
        travel.setParent(calendar);

        Assertions.assertTrue(calendar.travels().contain(travel));
    }

    @Test
    void getParent_GettingDone_true() throws MaximumSizeReachedException {
        travel.setParent(calendar);

        Assertions.assertEquals(calendar, travel.parent().get());
    }

}