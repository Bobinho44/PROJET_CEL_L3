package fr.unantes.sce.calendar;

import fr.unantes.sce.exception.InvalidArgumentException;
import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.wrapper.InitiallyEmptyMonoValuedAttribute;

import java.time.LocalDate;
import java.util.Objects;

public class Correspondence {

    private final InitiallyEmptyMonoValuedAttribute<Travel> travel = new InitiallyEmptyMonoValuedAttribute<>();
    private City startCity;
    private City destinationCity;
    private LocalDate startTime;
    private LocalDate arrivalTime;

    public Correspondence(Travel travel, City startCity, City destinationCity, LocalDate startTime, LocalDate arrivalTime) throws InvalidArgumentException, MaximumSizeReachedException {
        if (startTime.isAfter(arrivalTime)) {
            throw new InvalidArgumentException("arrivalTime is before startTime!");
        }

        this.setTravel(travel);
        this.startCity = startCity;
        this.destinationCity = destinationCity;
        this.startTime = startTime;
        this.arrivalTime = arrivalTime;
    }

    public InitiallyEmptyMonoValuedAttribute<Travel> travel() {
        return travel;
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

    public City getStartCity() {
        return startCity;
    }

    public void setStartCity(City startCity) {
        this.startCity = startCity;
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(City destinationCity) {
        this.destinationCity = destinationCity;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) throws InvalidArgumentException {
        if (startTime.isAfter(getArrivalTime())) {
            throw new InvalidArgumentException("Invalid argument. StartTime is after arrivalTime!");
        }

        this.startTime = startTime;
    }

    public LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDate arrivalTime) throws InvalidArgumentException {
        if (arrivalTime.isBefore(getStartTime())) {
            throw new InvalidArgumentException("Invalid argument. ArrivalTime is before startTime!");
        }

        this.arrivalTime = arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Correspondence that = (Correspondence) o;
        return Objects.equals(travel, that.travel) && Objects.equals(startCity, that.startCity) && Objects.equals(destinationCity, that.destinationCity) && Objects.equals(startTime, that.startTime) && Objects.equals(arrivalTime, that.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(travel, startCity, destinationCity, startTime, arrivalTime);
    }

}