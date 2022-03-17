package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;

import java.io.InvalidClassException;
import java.util.Objects;

public class Admin implements Status{
    private Person person;

    public Admin(Person person){
        this.person=person;
    }

    @Override
    public Calendar getCalendar() throws InvalidClassException {
        throw new InvalidClassException("Invalid operation. Only agent have a calendar");
    }

    @Override
    public void setCalendar(Calendar calendar) throws InvalidClassException {
        throw new InvalidClassException("Invalid operation. Only agent have a calendar");
    }

    @Override
    public void addTravelTo(Travel travel, Person agent) {
        try {
            agent.getCalendar().addTravel(travel);
        } catch (InvalidClassException e) {
            e.printStackTrace();
        }
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
        return "admin";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person2 = (Person) o;
        return person.getName().equals(person2.getName()) && getRole().equals(person2.getRole());
    }

    @Override
    public int hashCode() {
            return Objects.hash(person.getName(), getRole());
    }
}
