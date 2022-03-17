package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InvalidClassException;

import static org.atlanmod.testing.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private Person test1;
    private Person test2;

    @BeforeEach
    void setUp() throws InvalidClassException {
        test1=new Person("Cobaye1", "agent");
        test2=new Person("Cobaye2", "admin");
    }

    @AfterEach
    void tearDown() {
        test1=null;
        test2=null;
    }
    @Test
    void setRole_test1_toAdmin() throws InvalidClassException {
        test1.setRole("admin");
        assertThat(test1.getRole()=="admin");
    }
    @Test
    void setRole_test2_toAgent() throws InvalidClassException {
        test2.setRole("agent");
        assertThat(test2.getRole()=="agent");
    }

    @Test
    void getCalendar_test1() throws InvalidClassException {
        assertThat(test1.getCalendar()!=null);
    }

    @Test
    void addTravelToTest1_test2_works() throws InvalidClassException {
        test2.addTravelTo(new Travel(test1.getCalendar()), test1);
    }

    @Test
    void equalsTest1_test1_True(){
        assertThat(test1.equals(test1));
    }

    @Test
    void equalsTest2_test1_False(){
        assertThat(!(test1.equals(test2)));
    }

}
