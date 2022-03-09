package fr.unantes.sce.security;

import fr.unantes.sce.people.Person;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private UserManagement userManaging;
    private PasswordManagement passwordManaging;

    public UserManager() {
        userManaging = new UserManagement();
        passwordManaging = new PasswordManagement();
    }

    /**
     * Test if an user is registered in the manager
     * @param person - User to search for
     * @return True if the user is registered, False otherwise
     */
    public boolean hasUser(Person person) {
        return userManaging.hasUser(person);
    }

    /**
     * Add a new user to the manager
     * @param person - User to add
     * @param password - User's password
     * @return True if everything went smoothly, False otherwise
     * @throws IllegalArgumentException
     */
    public boolean addUser(Person person, String password) throws IllegalArgumentException {
        return userManaging.addUser(person, password, passwordManaging);
    }

    /**
     * Remove a user from the manager
     * @param person - User to remove
     * @return True if everything went smoothly, False otherwise
     */
    public boolean removeUser(Person person) {
        return userManaging.removeUser(person, passwordManaging.getUsersToPasswords());
    }

    /**
     * Valid a password
     * @param person - User associated to the password
     * @param password - password to validate
     * @return True if the password is valid, false otherwise
     */
    public boolean validatePassword(Person person, String password) {
        return passwordManaging.validatePassword(person, password, userManaging.getNamesToUsers());
    }

    /**
     * Encrypt a password
     * @param password - Password to encrypt
     * @return Encrypted password
     * @throws IllegalArgumentException
     */
    private String encryptPassword(String password) throws IllegalArgumentException {
        return passwordManaging.getEncryptPassword(password);
    }

    /**
     * Decrypt a password
     * @param encrypted - Password to decrypt
     * @return Decrypted password
     */
    private String decryptPassword(String encrypted) {
        return passwordManaging.getDecryptPassword(encrypted);
    }

    public String getDecryptPassword(String encrypted) {
        return decryptPassword(encrypted);
    }

    public String getEncryptPassword(String encrypted) {
        return encryptPassword(encrypted);
    }
}
