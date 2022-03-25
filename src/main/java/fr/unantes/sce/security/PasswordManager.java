package fr.unantes.sce.security;

import fr.unantes.sce.exception.AESEncryptionException;
import fr.unantes.sce.people.Person;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * PasswordManager manages the logic of the users' passwords
 */
public class PasswordManager {

    /**
     * Fields
     */
    private final Map<String, String> usersToPasswords = new HashMap<>();

    /**
     * Creates a new password manager
     */
    protected PasswordManager() {
    }

    /**
     * Gets the user password
     *
     * @param person the user
     * @return an optional value of the found password
     */
    @Nonnull
    private Optional<String> getPassword(@Nonnull Person person) {
        return Optional.ofNullable(this.usersToPasswords.get(person.name().get()));
    }

    /**
     * Sets the user password
     *
     * @param person   the affected user
     * @param password the password associated to the user
     */
    protected void setUserPassword(@Nonnull Person person, @Nonnull String password) throws AESEncryptionException {
        this.usersToPasswords.put(person.name().get(), encryptPassword(password));
    }

    /**
     * Unsets the user password
     *
     * @param person the affected user
     */
    protected void unsetUserPassword(@Nonnull Person person) {
        this.usersToPasswords.remove(person.name().get());
    }

    /**
     * Valids a password
     *
     * @param person   the user associated to the password
     * @param password the password to validate
     * @return true if the password is valid, false otherwise
     */
    protected boolean validatePassword(@Nonnull Person person, @Nonnull String password) throws AESEncryptionException {
        return getPassword(person).map(foundPassword -> decryptPassword(foundPassword).equals(password))
                .orElse(false);
    }

    /**
     * Encrypts a password
     *
     * @param password the password to encrypt
     * @return the encrypted password
     * @throws AESEncryptionException if there was an error with the encryption algorithm
     */
    @Nonnull
    private String encryptPassword(@Nonnull String password) throws AESEncryptionException {
        return AES.encrypt(password).orElseThrow(() ->
                new AESEncryptionException("Invalid operation: There was a problem with the deciphering!"));
    }

    /**
     * Decrypts a password
     *
     * @param encrypted the password to decrypt
     * @return the decrypted password
     * @throws AESEncryptionException if there was an error with the encryption algorithm
     */
    @Nonnull
    private String decryptPassword(@Nonnull String encrypted) throws AESEncryptionException {
        return AES.decrypt(encrypted).orElseThrow(() ->
                new AESEncryptionException("Invalid operation: There was a problem with the encryption!"));
    }

}