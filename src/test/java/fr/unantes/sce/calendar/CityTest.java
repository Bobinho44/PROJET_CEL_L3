package fr.unantes.sce.calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CityTest {

    private City city;

    @BeforeEach
    void setUp() {
        city = new City("France", "Nantes");
    }

    @Test
    void setCountry_SettingDone_true() {
        Assertions.assertEquals("France", city.country().get());

        city.country().set("Espagne");

        Assertions.assertEquals("Espagne", city.country().get());
    }

    @Test
    void setName_SettingDone_true() {
        Assertions.assertEquals("Nantes", city.name().get());

        city.name().set("Clisson");

        Assertions.assertEquals("Clisson", city.name().get());
    }

    @Test
    void equals_SameCountryAndName_true() {
        Assertions.assertEquals(new City("France", "Nantes"), city);
    }

    @Test
    void setCountry_CountryIsNull_ExceptionThrown() {
        Exception exception = Assertions.assertThrows(
                Exception.class,
                () -> city.country().set(null)
        );

        Assertions.assertEquals("Invalid operation. The value is null!", exception.getMessage());
    }

    @Test
    void setName_NameIsNull_ExceptionThrown() {
        Exception exception = Assertions.assertThrows(
                Exception.class,
                () -> city.name().set(null)
        );

        Assertions.assertEquals("Invalid operation. The value is null!", exception.getMessage());
    }

}