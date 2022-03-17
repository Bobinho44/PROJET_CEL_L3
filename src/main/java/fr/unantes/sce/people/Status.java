package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;

import java.io.InvalidClassException;

public interface Status {
    public Calendar getCalendar() throws InvalidClassException;
    public void setCalendar(Calendar calendar) throws InvalidClassException;
    public void addTravelTo(Travel travel, Person agent) throws InvalidClassException;
    public boolean equals(Object o);
    public int hashCode();
    public Status setRole(String role) throws InvalidClassException;
    public String getRole();
}

