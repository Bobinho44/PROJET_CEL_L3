package fr.unantes.sce.calendar;

import fr.unantes.sce.people.Person;
import org.atlanmod.testing.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TravelTest {

    private Travel travel;
    private Correspondence correspondence1;
    private Correspondence correspondence2;

    @BeforeEach
    public void setUp() throws Exception {
        Person person = new Person("Kylian", "agent");
        Calendar calendar = new Calendar(person);
        travel = new Travel(calendar);
        City city1 = new City("France", "Nantes");
        City city2 = new City("Corée du Sud", "Séoul");
        correspondence1 = new Correspondence(travel, city1, city2, 5, 1);
        correspondence2 = new Correspondence(travel, city2, city1, 1, 5);
    }

    @Test
    void addTravel_AdditionDone_true() {
        travel.getSteps().add(correspondence1);
        travel.getSteps().add(correspondence2);
        Assertions.assertThat(travel.getFirstStep().equals(correspondence1));
    }

    @Test
    void removeTravel_DeletionDone_true() {
        travel.getSteps().add(correspondence1);
        travel.getSteps().add(correspondence2);

        Assertions.assertThat(travel.getLastStep().equals(correspondence2));
    }

}