package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;

import java.io.InvalidClassException;
import java.util.Objects;

/**
 * A Generic person, which can be an agent or an administrator
 */
public class Person {
    public String name;
    public Status role;
    protected Calendar calendar;

    public Person(String name, String role) throws InvalidClassException {
        this.name = name;
        calendar=new Calendar(this);
        switch (role) {
            case "agent" : this.role =new Agent(this); break;
            case "admin" : this.role = new Admin(this); break;
            default : throw new InvalidClassException("Invalid role supplied. A person can only be an agent or an admin");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role.getRole();
    }

    public void setRole(String role) throws InvalidClassException {
        this.role = this.role.setRole(role);
    }

    public Calendar getCalendar() throws InvalidClassException {
        return role.getCalendar();
    }

    public void setCalendar(Calendar calendar) throws InvalidClassException {
        role.setCalendar(calendar);
    }

    public void addTravelTo(Travel travel, Person agent) throws InvalidClassException {
        role.addTravelTo(travel, agent);
    }

    @Override
    public boolean equals(Object o) {
        return this.role.equals(o);
    }

    @Override
    public int hashCode() {
        return this.role.hashCode();
    }
}