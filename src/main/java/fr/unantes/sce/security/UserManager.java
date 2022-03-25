package fr.unantes.sce.security;

import fr.unantes.sce.people.Person;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * PasswordManager manages the logic of users
 */
public class UserManager {

    /**
     * Fields
     */
    private final Map<String, Person> namesToUsers = new HashMap<>();
    private final PasswordManager passwordManager = new PasswordManager();

    /**
     * Gets the registered user
     *
     * @param person the user to search for
     * @return an optional value of the found user associated to the person
     */
    @Nonnull
    private Optional<Person> getUser(@Nonnull Person person) {
        return Optional.ofNullable(this.namesToUsers.get(person.name().get()));
    }

    /**
     * Checks if the user is already registered
     *
     * @param person the user to search for
     * @return true the user is already registered, false otherwise
     */
    public boolean hasUser(@Nonnull Person person) {
        return getUser(person).isPresent();
    }

    /**
     * Registers the user
     *
     * @param person   the registered user
     * @param password the password associated to the user
     * @throws IllegalArgumentException if the user is already registered
     */
    public void addUser(@Nonnull Person person, @Nonnull String password) {
        if (hasUser(person)) {
            throw new IllegalArgumentException("Invalid argument: the person is already registered.");
        }

        this.namesToUsers.put(person.name().get(), person);
        this.passwordManager.setUserPassword(person, password);

    }

    /**
     * Unregisters the user
     *
     * @param person the unregistered user
     * @throws IllegalArgumentException if the user is not registered
     */
    public void removeUser(@Nonnull Person person) throws IllegalArgumentException {
        Person foundPerson = getUser(person).orElseThrow(() ->
                new IllegalArgumentException("Invalid argument: the person is not registered."));

        this.namesToUsers.remove(foundPerson.name().get());
        this.passwordManager.unsetUserPassword(foundPerson);
    }

    /**
     * Valids the user password
     *
     * @param person   the checked user
     * @param password the checked password
     * @return true if the password is valid, false otherwise
     */
    public boolean validatePassword(@Nonnull Person person, @Nonnull String password) {
        return this.passwordManager.validatePassword(person, password);
    }

}