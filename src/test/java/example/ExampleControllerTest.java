package example;

import example.person.Person;
import example.person.PersonRepository;
import example.weather.WeatherResponse;
import example.weather.WeatherClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExampleControllerTest {

    private ExampleController exampleController;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private WeatherClient weatherClient;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        exampleController = new ExampleController(personRepository, weatherClient);
    }

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        assertThat(exampleController.hello(), is("Hello World!"));
    }

    @Test
    public void shouldReturnFullNameOfAPerson() throws Exception {
        Person name = new Person("Suraj", "Nayak");
        given(personRepository.findByLastName("Nayak")).willReturn(Optional.of(name));

        String greeting = exampleController.hello("Nayak");

        assertThat(greeting, is("Hello Suraj Nayak!"));
    }

    @Test
    public void shouldTellIfPersonIsUnknown() throws Exception {
        given(personRepository.findByLastName(anyString())).willReturn(Optional.empty());

        String greeting = exampleController.hello("Nayak");

        assertThat(greeting, is("Who is this 'Nayak' you're talking about?"));
    }

    @Test
    public void shouldReturnWeatherClientResult() throws Exception {
        WeatherResponse weatherResponse = new WeatherResponse("Pune, 8°C raining");
        given(weatherClient.fetchWeather()).willReturn(Optional.of(weatherResponse));

        String weather = exampleController.weather();

        assertThat(weather, is("Pune, 8°C raining"));
    }

    @Test
    public void shouldReturnErrorMessageIfWeatherClientIsUnavailable() throws Exception {
        given(weatherClient.fetchWeather()).willReturn(Optional.empty());

        String weather = exampleController.weather();

        assertThat(weather, is("Sorry, I couldn't fetch the weather for you :("));
    }
}