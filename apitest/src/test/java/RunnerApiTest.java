import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Created by gvaldes
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:target/cucumber"},
        features = "classpath:feature/geocoding.feature")
public class RunnerApiTest {

    @Before
    public void init() throws IOException {

    }
}
