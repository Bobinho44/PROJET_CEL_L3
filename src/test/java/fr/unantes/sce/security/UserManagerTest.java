package fr.unantes.sce.security;

import fr.unantes.sce.people.Agent;
import fr.unantes.sce.people.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InvalidClassException;

import static org.atlanmod.testing.Assertions.assertThat;

class UserManagerTest {
    private UserManager m1;

    @BeforeEach
    void setUp() {
        m1 = new UserManager();
    }

    @AfterEach
    void tearDown() {
        m1 = null;
    }

    @Test
    void hasUser_agentTom_True() throws InvalidClassException {
        Person a1 = new Person("Tom", Agent.INSTANCE);
        m1.addUser(a1, "123");
        assertThat(m1.hasUser(a1));
    }

    @Test
    void removeUser_agentTome_True() throws InvalidClassException {
        Person a1 = new Person("Tom", Agent.INSTANCE);
        m1.addUser(a1, "123");
        m1.removeUser(a1);
        assertThat(!(m1.hasUser(a1)));
    }

    @Test
    void hasUser_agentGeorge_False() throws InvalidClassException {
        assertThat(!(m1.hasUser(new Person("George", Agent.INSTANCE))));
    }

    @Test
    void validatePassword_ofTom_True() throws InvalidClassException {
        Person a1 = new Person("Tom", Agent.INSTANCE);
        m1.addUser(a1, "123");
        assertThat(m1.validatePassword(a1, "123"));
    }

    @Test
    void validatePassword_ofTom_False() throws InvalidClassException {
        Person a1 = new Person("Tom", Agent.INSTANCE);
        m1.addUser(a1, "123");
        assertThat(!(m1.validatePassword(a1, "113")));
    }

    @Test
    void decryptEncrypt_123_True() throws InvalidClassException {
        assertThat("123" == m1.getDecryptPassword(m1.getEncryptPassword("123")));
    }
}
