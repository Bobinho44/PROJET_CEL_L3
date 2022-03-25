package fr.unantes.sce.calendar;

import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.wrapper.Interval;
import fr.unantes.sce.wrapper.MonoValuedAttribute;
import fr.unantes.sce.wrapper.NullableMonoValuedAttribute;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Objects;

/**
 * A Correspondence goes from one place to another, with a departure date and an arrival date
 */
public class Correspondence {

    /**
     * Fields
     */
    private final NullableMonoValuedAttribute<Travel> travel = new NullableMonoValuedAttribute<>();
    private final MonoValuedAttribute<City> origin;
    private final MonoValuedAttribute<City> destination;
    private final Interval<ChronoLocalDate> timeInterval;

    /**
     * Creates a new correspondence
     *
     * @param travel      the travel associated to the correspondence
     * @param origin      the origin city associated to the correspondence
     * @param destination destination city associated to the correspondence
     * @param start       the start date city associated to the correspondence
     * @param arrival     the arrival date associated to the correspondence
     */
    public Correspondence(@Nonnull Travel travel, @Nonnull City origin, @Nonnull City destination, @Nonnull LocalDate start, @Nonnull LocalDate arrival) {
        this.setTravel(travel);
        this.origin = new MonoValuedAttribute<>(origin);
        this.destination = new MonoValuedAttribute<>(destination);
        this.timeInterval = new Interval<>(start, arrival);
    }

    /**
     * Gets the travel wrapper
     *
     * @return the travel wrapper
     */
    @Nonnull
    protected NullableMonoValuedAttribute<Travel> travel() {
        return travel;
    }

    /**
     * Gets the origin wrapper
     *
     * @return the origin wrapper
     */
    @Nonnull
    public MonoValuedAttribute<City> origin() {
        return origin;
    }

    /**
     * Gets the destination wrapper
     *
     * @return the destination wrapper
     */
    @Nonnull
    public MonoValuedAttribute<City> destination() {
        return destination;
    }

    /**
     * Gets the time interval wrapper
     *
     * @return the time interval wrapper
     */
    @Nonnull
    public Interval<ChronoLocalDate> timeInterval() {
        return timeInterval;
    }

    /**
     * Sets the new correspondence travel
     *
     * @param travel the new correspondence travel
     * @throws MaximumSizeReachedException if the travel list is full
     */
    public void setTravel(@Nonnull Travel travel) throws MaximumSizeReachedException {
        if (isAlreadyLinkedWithATravel()) {
            this.travel.get().steps().basicRemove(this);
        }

        this.travel.set(travel);
        travel.steps().add(this);
    }

    /**
     * Checks if the correspondence is already linked with another travel
     *
     * @return true if the correspondence is already linked with another travel, false otherwise
     */
    private boolean isAlreadyLinkedWithATravel() {
        return Objects.nonNull(this.travel.get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Correspondence correspondence = (Correspondence) o;
        return Objects.equals(travel.get(), correspondence.travel().get()) &&
                Objects.equals(origin.get(), correspondence.origin().get()) &&
                Objects.equals(destination.get(), correspondence.destination().get()) &&
                Objects.equals(timeInterval.getBegin(), correspondence.timeInterval().getBegin()) &&
                Objects.equals(timeInterval.getEnd(), correspondence.timeInterval().getEnd());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(travel.get(), origin.get(), destination.get(), timeInterval.getBegin(), timeInterval.getEnd());
    }

}