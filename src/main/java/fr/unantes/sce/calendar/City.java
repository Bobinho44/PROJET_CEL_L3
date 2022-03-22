package fr.unantes.sce.calendar;

import fr.unantes.sce.wrapper.MonoValuedAttribute;

import java.util.Objects;

/**
 * A city
 */
public class City {

    private final MonoValuedAttribute<String> country;
    private final MonoValuedAttribute<String> name;

    public City(String country, String name) {
        this.country = new MonoValuedAttribute<>(country);
        this.name = new MonoValuedAttribute<>(name);
    }

    public MonoValuedAttribute<String> country() {
        return country;
    }

    public MonoValuedAttribute<String> name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(country(), city.country()) &&
                Objects.equals(name(), city.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(country(), name());
    }

}