package springbootvuejs.repository;

import springbootvuejs.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository users;

    private final User norbertSiegmund = new User("Norbert", "Siegmund");
    private final User jonasHecht = new User("Jonas", "Hecht");

    @BeforeEach
    public void fillSomeDataIntoOurDb() {
        // Add new Users to Database
        entityManager.persist(norbertSiegmund);
        entityManager.persist(jonasHecht);
    }

//    @Test
//    public void testFindByLastName() throws Exception {
//        // Search for specific User in Database according to lastname
//        List<User> usersWithLastNameSiegmund = users.findByLastName("Siegmund");
//
//        assertThat(usersWithLastNameSiegmund, contains(norbertSiegmund));
//    }

//    @Test
//    public void testFindByFirstName() throws Exception {
//        // Search for specific User in Database according to firstname
//        List<User> usersWithFirstNameJonas = users.findByFirstName("Jonas");
//
//        assertThat(usersWithFirstNameJonas, contains(jonasHecht));
//    }

}