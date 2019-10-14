package example;

import example.person.Person;
import example.person.PersonRepository;
import example.weather.WeatherResponse;
import example.weather.WeatherClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ExampleController.class)
public class ExampleControllerAPITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private WeatherClient weatherClient;

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(content().string("Hello World!"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReturnFullName() throws Exception {
        Person name = new Person("Suraj", "Nayak");
        given(personRepository.findByLastName("Suraj")).willReturn(Optional.of(name));

        mockMvc.perform(get("/hello/Suraj"))
                .andExpect(content().string("Hello Suraj Nayak!"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReturnCurrentWeather() throws Exception {
        WeatherResponse weatherResponse = new WeatherResponse("Pune, 8°C raining");
        given(weatherClient.fetchWeather()).willReturn(Optional.of(weatherResponse));

        mockMvc.perform(get("/weather"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Pune, 8°C raining"));
    }
}
