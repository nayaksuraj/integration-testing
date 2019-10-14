package example.weather;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;



@RunWith(SpringRunner.class)
public class ExternalWeatherIntegrationWireMockStandaloneServerTest {


    private static String baseUrl() {
        return "http://localhost:8091/weather";
    }

    @Test
    public void shouldReturnYesterdaysWeather() throws Exception {

        when()
                .get(String.format(baseUrl()))
                .then()
                .statusCode(is(200))
                .body(containsString("Clear"));
    }

}
