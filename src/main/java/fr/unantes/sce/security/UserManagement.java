package fr.unantes.sce.security;

import fr.unantes.sce.people.Person;

import java.util.HashMap;
import java.util.Map;

public class UserManagement {
    private Map<String, Person> namesToUsers;

    public UserManagement(){
      namesToUsers = new HashMap<>();
    }

        /**
         * Test if an user is registered in the manager
         * @param person - User to search for
         * @return True if the user is registered, False otherwise
         */
        public boolean hasUser(Person person) {
            return namesToUsers.containsKey(person.name().get());
        }

        /**
         * Add a new user to the manager
         * @param person - User to add
         * @param password - User's password
         * @return True if everything went smoothly, False otherwise
         * @throws IllegalArgumentException
         */
        public boolean addUser(Person person, String password, PasswordManagement pM) throws IllegalArgumentException {
            if (namesToUsers.containsKey(person.name().get())) {
                throw new IllegalArgumentException("Invalid argument: the person is already registered.");
            }
            namesToUsers.put(person.toString(), person);
            pM.getUsersToPasswords().put(person.name().get(), pM.getEncryptPassword(password));
            return true;
        }

        /**
         * Remove a user from the manager
         * @param person - User to remove
         * @return True if everything went smoothly, False otherwise
         */
        public boolean removeUser(Person person, Map<String, String> usersToPasswords) {
            if (namesToUsers.containsKey(person.name().get())) {
                Person p = namesToUsers.get(person.name().get());
                usersToPasswords.remove(p.name().get());
                namesToUsers.remove(p.name().get());
            }
            return true;
        }

    public Map<String, Person> getNamesToUsers() {
        return namesToUsers;
    }
}
