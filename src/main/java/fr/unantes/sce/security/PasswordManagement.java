package fr.unantes.sce.security;

import fr.unantes.sce.people.Person;

import java.util.HashMap;
import java.util.Map;

public class PasswordManagement {
  private Map<String, String> usersToPasswords;

      public PasswordManagement(){
        usersToPasswords = new HashMap<>();
      }

      /**
       * Valid a password
       * @param person - User associated to the password
       * @param password - password to validate
       * @return True if the password is valid, false otherwise
       */
      public boolean validatePassword(Person person, String password, Map<String, Person> namesToUsers) {
          if (namesToUsers.containsKey(person.getName())) {
              Person p = namesToUsers.get(person.getName());
              String reference = usersToPasswords.get(p.getName());
              return decryptPassword(reference).equals(password);
          }
          return false;
      }

      /**
       * Encrypt a password
       * @param password - Password to encrypt
       * @return Encrypted password
       * @throws IllegalArgumentException
       */
      private String encryptPassword(String password) throws IllegalArgumentException {
          if (password.contains("a")) {
              throw new IllegalArgumentException("The password contains unsecure characters, cannot perform encryption.");
          }
          return password.replaceAll("a", "e");
      }

      /**
       * Decrypt a password
       * @param encrypted - Password to decrypt
       * @return Decrypted password
       */
      private String decryptPassword(String encrypted) {
          return encrypted.replaceAll("e", "a");
      }

      public Map<String, String> getUsersToPasswords() {
          return this.usersToPasswords;
      }

      public String getEncryptPassword(String password){ return encryptPassword(password); }
      public String getDecryptPassword(String password){ return decryptPassword(password); }

}
