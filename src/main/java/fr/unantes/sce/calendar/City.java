package fr.unantes.sce.calendar;

import fr.unantes.sce.wrapper.MonoValuedAttribute;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * A City stores a country and a name
 */
public class City {

    /**
     * Fields
     */
    private final MonoValuedAttribute<String> country;
    private final MonoValuedAttribute<String> name;

    /**
     * Creates a new city
     *
     * @param country the country name associated to the city
     * @param name    the city name associated to the city
     */
    public City(@Nonnull String country, @Nonnull String name) {
        this.country = new MonoValuedAttribute<>(country);
        this.name = new MonoValuedAttribute<>(name);
    }

    /**
     * Gets the country wrapper
     *
     * @return the country wrapper
     */
    @Nonnull
    public MonoValuedAttribute<String> country() {
        return country;
    }

    /**
     * Gets the name wrapper
     *
     * @return the name wrapper
     */
    @Nonnull
    public MonoValuedAttribute<String> name() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(country().get(), city.country().get()) &&
                Objects.equals(name().get(), city.name().get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(country().get(), name().get());
    }

}