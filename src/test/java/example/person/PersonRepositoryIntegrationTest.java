/**
    In memory database testing.
 */

package example.person;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryIntegrationTest {

    @Autowired
    private PersonRepository personRepository;

    @After
    public void tearDown() throws Exception {
        personRepository.deleteAll();
    }

    @Test
    public void shouldSaveAndFetchPerson() throws Exception {
        Person name = new Person("Suraj", "Nayak");
        personRepository.save(name);

        Optional<Person> maybeSuraj = personRepository.findByLastName("Nayak");
        assertThat(maybeSuraj, is(Optional.of(name)));
    }
}