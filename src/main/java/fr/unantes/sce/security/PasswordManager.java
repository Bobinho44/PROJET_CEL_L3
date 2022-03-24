package fr.unantes.sce.security;

import fr.unantes.sce.people.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PasswordManager {

    private final Map<String, String> usersToPasswords = new HashMap<>();

    protected PasswordManager() {}

    private Optional<String> getPassword(String user) {
        return Optional.ofNullable(usersToPasswords.get(user));
    }

    protected void setUserPassword(Person person, String password) {
        usersToPasswords.put(person.name().get(), encryptPassword(password));
    }

    protected void unsetUserPassword(Person person) {
        usersToPasswords.remove(person.name().get());
    }

    /**
     * Valid a password
     * @param person - User associated to the password
     * @param password - password to validate
     * @return True if the password is valid, false otherwise
     */
    protected boolean validatePassword(Person person, String password) {
        Optional<String> foundPassword = getPassword(person.name().get());

        if (foundPassword.isEmpty()) {
            return false;
        }

        return decryptPassword(foundPassword.get()).equals(password);
    }

    /**
     * Encrypt a password
     * @param password - Password to encrypt
     * @return Encrypted password
     */
    private String encryptPassword(String password) {
        return AES.encrypt(password);
    }

    /**
     * Decrypt a password
     * @param encrypted - Password to decrypt
     * @return Decrypted password
     */
    private String decryptPassword(String encrypted) {
        return AES.decrypt(encrypted);
    }

}