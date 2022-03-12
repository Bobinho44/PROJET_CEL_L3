package fr.unantes.sce.calendar;

import org.atlanmod.commons.Guards;

import java.time.LocalDate;
import java.util.Objects;

public class Correspondence {

    private Travel travel;
    private City startCity;
    private City destinationCity;
    private LocalDate startTime;
    private LocalDate arrivalTime;

    public Correspondence(Travel travel, City startCity, City destinationCity, LocalDate startTime, LocalDate arrivalTime) {
        Guards.checkArgument(startTime.isBefore(arrivalTime), "arrivalTime is before startTime!");

        this.travel = travel;
        this.startCity = startCity;
        this.destinationCity = destinationCity;
        this.startTime = startTime;
        this.arrivalTime = arrivalTime;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
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

    public void setStartTime(LocalDate startTime) {
        Guards.checkArgument(startTime.isBefore(getArrivalTime()), "startTime is after this correspondence arrivalTime!");

        this.startTime = startTime;
    }

    public LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDate arrivalTime) {
        Guards.checkArgument(arrivalTime.isAfter(getStartTime()), "arrivalTime is before this correspondence startTime!");

        this.arrivalTime = arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Correspondence that = (Correspondence) o;
        return getStartCity().equals(that.getStartCity()) &&
                getDestinationCity().equals(that.getDestinationCity()) &&
                getStartTime() == that.getStartTime() &&
                getArrivalTime() == that.getArrivalTime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTravel(), getStartCity(), getDestinationCity(), getStartTime(), getArrivalTime());
    }

}