package fr.unantes.sce.security;

import fr.unantes.sce.people.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserManager {

    private final Map<String, Person> namesToUsers = new HashMap<>();
    private final PasswordManager passwordManager = new PasswordManager();

    private Optional<Person> getUser(Person person) {
        return Optional.ofNullable(namesToUsers.get(person.name().get()));
    }

    /**
     * Test if an user is registered in the manager
     *
     * @param person - User to search for
     * @return True if the user is registered, False otherwise
     */
    public boolean hasUser(Person person) {
        return getUser(person).isPresent();
    }

    /**
     * Add a new user to the manager
     *
     * @param person   - User to add
     * @param password - User's password
     * @throws IllegalArgumentException the user is already registered
     */
    public void addUser(Person person, String password) throws IllegalArgumentException {
        if (hasUser(person)) {
            throw new IllegalArgumentException("Invalid argument: the person is already registered.");
        }

        namesToUsers.put(person.name().get(), person);
        passwordManager.setUserPassword(person, password);

    }

    /**
     * Remove a user from the manager
     *
     * @param person - User to remove
     * @throws IllegalArgumentException the user is not registered
     */
    public void removeUser(Person person) throws IllegalArgumentException {
        Optional<Person> foundPerson = getUser(person);

        if (foundPerson.isEmpty()) {
            throw new IllegalArgumentException("Invalid argument: the person is not registered.");
        }

        namesToUsers.remove(foundPerson.get().name().get());
        passwordManager.unsetUserPassword(foundPerson.get());
    }

    /**
     * Valid a password
     *
     * @param person   - User associated to the password
     * @param password - password to validate
     * @return True if the password is valid, false otherwise
     */
    public boolean validatePassword(Person person, String password) {
        return passwordManager.validatePassword(person, password);
    }

}