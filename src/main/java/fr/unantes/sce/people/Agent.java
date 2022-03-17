package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;

import java.io.InvalidClassException;
import java.util.Objects;

public class Agent implements Status{
    protected Calendar calendar;
    private Person person;

    public Agent(Person person){
        calendar=new Calendar(person);
        this.person=person;
    }

    @Override
    public Calendar getCalendar() {
        return calendar;
    }

    @Override
    public void setCalendar(Calendar calendar) {
        this.calendar=calendar;
    }

    @Override
    public void addTravelTo(Travel travel, Person agent) throws InvalidClassException {
        throw new InvalidClassException("Invalid operation. Only an administrator can add travel to an agent.");
    }

    @Override
    public Status setRole(String role) throws InvalidClassException {
        switch (role) {
            case "agent" : return new Agent(person);
            case "admin" : return new Admin(person);
            default : throw new InvalidClassException("Invalid role supplied. A person can only be an agent or an admin");
        }
    }

    @Override
    public String getRole() {
        return "agent";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person2 = (Person) o;
        if (getRole().equals(person2.getRole()) && person2.getRole().equals("agent")) {
            try {
                return person.getName().equals(person2.getName()) && getCalendar().equals(person2.getCalendar());
            } catch (InvalidClassException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(person.getName(), getRole(), getCalendar());
    }

}
