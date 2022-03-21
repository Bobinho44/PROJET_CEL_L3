package fr.unantes.sce.calendar;

import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.exception.MinimumSizeReachedException;
import fr.unantes.sce.people.Agent;
import fr.unantes.sce.people.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class TravelTest {

    private Travel travel1;
    private Travel travel2;
    private Travel travel3;
    private Correspondence correspondence1;
    private Correspondence correspondence2;
    private Correspondence correspondence3;
    private Correspondence correspondence5;
    private Calendar calendar;
    private final List<Correspondence> steps = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Person person = new Person("Kylian", Agent.INSTANCE);
        City city1 = new City("France", "Nantes");
        City city2 = new City("Corée du Sud", "Séoul");
        LocalDate date1 = LocalDate.of(2000, 2, 15);
        LocalDate date2 = LocalDate.of(2000, 2, 16);

        calendar = new Calendar(person);
        travel1 = new Travel(new Calendar(person));
        travel2 = new Travel(new Calendar(person));
        travel3 = new Travel(new Calendar(person));
        correspondence1 = new Correspondence(new Travel(new Calendar(person)), city1, city2, date1, date2);
        correspondence2 = new Correspondence(new Travel(new Calendar(person)), city2, city1, date1, date2);
        correspondence3 = new Correspondence(travel2, city2, city1, date1, date2);
        travel2.addStep(new Correspondence(travel2, city2, city1, date1, date2));
        correspondence5 = new Correspondence(travel3, city2, city1, date1, date2);
        for (int i = 0; i < 11; i++) {
            steps.add(new Correspondence(new Travel(new Calendar(person)), city1, city2, date1, date2));
        }
    }

    @Test
    void addStep_AdditionDone_true() {
        Assertions.assertTrue(travel1.steps().isEmpty());

        travel1.addStep(correspondence1);
        travel1.addStep(correspondence2);

        Assertions.assertEquals(correspondence1, travel1.getFirstStep());
        Assertions.assertEquals(correspondence2, travel1.getLastStep());
    }

    @Test
    void removeStep_DeletionDone_true() {
        travel1.addStep(correspondence1);
        travel1.addStep(correspondence2);

        Assertions.assertEquals(correspondence1, travel1.getFirstStep());

        travel1.removeStep(correspondence1);

        Assertions.assertEquals(correspondence2, travel1.getFirstStep());
    }

    @Test
    void setParent_SettingDone_true() {
        Assertions.assertFalse(calendar.travels().contain(travel1));

        travel1.setParent(calendar);

        Assertions.assertTrue(calendar.travels().contain(travel1));
    }

    @Test
    void getParent_GettingDone_true() {
        Assertions.assertNotEquals(calendar, travel1.parent().get());

        travel1.setParent(calendar);

        Assertions.assertEquals(calendar, travel1.parent().get());
    }

    @Test
    void addStep_Add11Steps_ExceptionThrown() {
        MaximumSizeReachedException exception = Assertions.assertThrows(
                MaximumSizeReachedException.class,
                () -> {
                    for (Correspondence step : steps) {
                        travel1.addStep(step);
                    }
                }
        );

        Assertions.assertEquals("Invalid operation. The multiValuedAttribute is already full!", exception.getMessage());
    }

    @Test
    void addStep_BidirectionalAssociationDone_True() {
        Assertions.assertNotEquals(travel1, correspondence2.travel().get());

        travel1.addStep(correspondence2);

        Assertions.assertEquals(travel1, correspondence2.travel().get());
    }

    @Test
    void removeStep_BidirectionalAssociationRevoked_True() {
    Assertions.assertEquals(travel2, correspondence3.travel().get());

    travel2.removeStep(correspondence3);

    Assertions.assertNotEquals(travel2, correspondence3.travel().get());
    }

    @Test
    void getSteps_GettingDone_True() {
        Assertions.assertEquals(correspondence3, travel2.steps().get(0));
    }

    @Test
    void removeStep_StepIsNotLinkedWithTheTravel_ExceptionThrown() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> travel2.removeStep(correspondence2)
        );

        Assertions.assertEquals("Invalid operation. The multiValuedAttribute does not contain this value!", exception.getMessage());
    }

    @Test
    void removeStep_StepsWillBeEmpty_ExceptionThrown() {
        MinimumSizeReachedException exception = Assertions.assertThrows(
                MinimumSizeReachedException.class,
                () -> travel3.removeStep(correspondence5)
        );

        Assertions.assertEquals("Invalid operation. The multiValuedAttribute has reached its minimum size!", exception.getMessage());
    }

}