package fr.unantes.sce.calendar;

import fr.unantes.sce.exception.InvalidArgumentException;
import org.atlanmod.commons.Guards;

import java.time.LocalDate;
import java.util.Objects;

public class Correspondence {

    private Travel travel;
    private City startCity;
    private City destinationCity;
    private LocalDate startTime;
    private LocalDate arrivalTime;

    public Correspondence(Travel travel, City startCity, City destinationCity, LocalDate startTime, LocalDate arrivalTime) throws InvalidArgumentException {
        if (startTime.isAfter(arrivalTime)) {
            throw new InvalidArgumentException("arrivalTime is before startTime!");
        }

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