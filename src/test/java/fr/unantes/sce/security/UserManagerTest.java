package fr.unantes.sce.security;

import fr.unantes.sce.people.Agent;
import fr.unantes.sce.people.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserManagerTest {

    private UserManager userManager;
    private Person person1;
    private Person person2;

    @BeforeEach
    void setUp() {
        userManager = new UserManager();
        person1 = new Person("Tom", Agent.INSTANCE);
        person2 = new Person("Bob", Agent.INSTANCE);
        userManager.addUser(person2, "abc");
    }

    @Test
    void hasUser_UserIsAdded_True() {
        Assertions.assertTrue(userManager.hasUser(person2));
    }

    @Test
    void hasUser_UserIsNotAdded_False() {
        Assertions.assertFalse(userManager.hasUser(person1));
    }

    @Test
    void addUser_addingDone_True() {
        Assertions.assertFalse(userManager.hasUser(person1));

        userManager.addUser(person1, "123");

        Assertions.assertTrue(userManager.hasUser(person1));
    }

    @Test
    void removeUser_removingDone_False() {
        Assertions.assertTrue(userManager.hasUser(person2));

        userManager.removeUser(person2);

        Assertions.assertFalse(userManager.hasUser(person2));
    }

    @Test
    void validatePassword_validPassword_True() {
        Assertions.assertTrue(userManager.validatePassword(person2, "abc"));
    }

    @Test
    void validatePassword_invalidPassword_False() {
        Assertions.assertFalse(userManager.validatePassword(person2, "Abc"));
    }

}