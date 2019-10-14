/**
 * Weather Client Testing
 */

package example.weather;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class WeatherClientTest {

    private WeatherClient weatherClient;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        weatherClient = new WeatherClient(restTemplate, "http://localhost:8089", "someAppId");
    }

    @Test
    public void shouldCallWeatherService() throws Exception {
        WeatherResponse expectedResponse = new WeatherResponse("Clear");
        given(restTemplate.getForObject("http://localhost:8089/someAppId/18.5204,73.8567", WeatherResponse.class))
                .willReturn(expectedResponse);

        Optional<WeatherResponse> actualResponse = weatherClient.fetchWeather();

        assertThat(actualResponse, is(Optional.of(expectedResponse)));

        //System.out.println(actualResponse);
    }

    @Test
    public void shouldReturnEmptyOptionalIfWeatherServiceIsUnavailable() throws Exception {
        given(restTemplate.getForObject("http://localhost:8089/someAppId/18.5204,73.8567", WeatherResponse.class))
                .willThrow(new RestClientException("something went wrong"));

        Optional<WeatherResponse> actualResponse = weatherClient.fetchWeather();

        assertThat(actualResponse, is(Optional.empty()));


        //System.out.println(actualResponse);

    }

}