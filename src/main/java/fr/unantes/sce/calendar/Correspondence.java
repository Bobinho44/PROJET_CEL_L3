package fr.unantes.sce.calendar;

import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.wrapper.Interval;
import fr.unantes.sce.wrapper.MonoValuedAttribute;
import fr.unantes.sce.wrapper.NullableMonoValuedAttribute;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Objects;

public class Correspondence {

    private final NullableMonoValuedAttribute<Travel> travel = new NullableMonoValuedAttribute<>();
    private final MonoValuedAttribute<City> origin;
    private final MonoValuedAttribute<City> destination;
    private final Interval<ChronoLocalDate> timeInterval;

    public Correspondence(Travel travel, City startCity, City destinationCity, LocalDate startTime, LocalDate arrivalTime) {
        this.setTravel(travel);
        this.origin = new MonoValuedAttribute<>(startCity);
        this.destination = new MonoValuedAttribute<>(destinationCity);
        this.timeInterval = new Interval<>(startTime, arrivalTime);
    }

    protected NullableMonoValuedAttribute<Travel> travel() {
        return travel;
    }

    public MonoValuedAttribute<City> origin() {
        return origin;
    }

    public MonoValuedAttribute<City> destination() {
        return destination;
    }

    public Interval<ChronoLocalDate> timeInterval() {
        return timeInterval;
    }

    public void setTravel(Travel travel) throws MaximumSizeReachedException {
        if (isAlreadyLinkedWithATravel()) {
            travel().get().steps().basicRemove(this);
        }

        travel().set(travel);
        travel.steps().add(this);
    }

    private boolean isAlreadyLinkedWithATravel() {
        return travel().get() != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Correspondence correspondence = (Correspondence) o;
        return Objects.equals(travel().get(), correspondence.travel().get()) &&
                Objects.equals(origin().get(), correspondence.origin().get()) &&
                Objects.equals(destination().get(), correspondence.destination().get()) &&
                Objects.equals(timeInterval().getBegin(), correspondence.timeInterval().getBegin()) &&
                Objects.equals(timeInterval().getEnd(), correspondence.timeInterval().getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(travel().get(), origin().get(), destination().get(), timeInterval().getBegin(), timeInterval().getEnd());
    }

}