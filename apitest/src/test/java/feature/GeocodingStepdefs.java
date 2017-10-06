package feature;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.gustavo.assinment.helper.GeocodingErrorResponse;
import com.gustavo.assinment.helper.GeocodingResponse;
import com.gustavo.assinment.helper.RestConsumer;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.junit.Assert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Created by gvaldes
 */
public class GeocodingStepdefs  {

    private HttpResponse responseHolder;
    private static final String  ADDRESS = "address";
    private static final String  LATITUDE = "latitude";
    private static final String  LONGITUDE = "longitude";
    private static final String  ERROR = "error";
    private static final String  ERROR_DESCRIPTION = "errorDescription";
    private static final String  REFERENCE = "reference";

    private final WireMockServer wireMockServer = new WireMockServer(1580);

    @Given("^External services are available$")
    public void externalServicesAreRunning() throws Exception {
        String santiago = readFile("Santiago.xml");

        wireMockServer.start();
        configureFor("localhost", 1580);
        stubFor(get(urlEqualTo("/maps/api/geocode/xml?address=Santiago"))
                .willReturn(
                        aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/xml")
                                .withBody(santiago)));

    }

    @Given("^External services are not available$")
    public void externalServicesAreNotRunning() throws Exception {
        if(wireMockServer.isRunning()){
            wireMockServer.stop();
        }
    }

    @When("^I made a GET request to the geocoding endppoint with address (\\w+)$")
    public void makeRequestWithAddress(String address) throws Throwable {
        responseHolder = new RestConsumer().get(address);
    }

    @Then("^I receive HTTP status (\\d+)$")
    public void validateHttpResponseCode(int expectedHttpCode) {
        Assert.assertEquals(expectedHttpCode, responseHolder.getStatusLine().getStatusCode());
    }

    @And("^The values received are$")
    public void validateResponseContent(DataTable expectedValues) throws IOException {
        List<Map<String, String>> expected = expectedValues.asMaps(String.class, String.class);
        GeocodingResponse geocodingResponse = RestConsumer.convertResponseToGeodingModel(responseHolder);
        for (Map<String, String> map : expected) {
            if(map.containsKey(ADDRESS)){
                Assert.assertEquals(map.get(ADDRESS), geocodingResponse.getAddress());
            }
            if(map.containsKey(LATITUDE)){
                Assert.assertEquals(map.get(LATITUDE), geocodingResponse.getLatitude());
            }
            if(map.containsKey(LONGITUDE)){
                Assert.assertEquals(map.get(LONGITUDE), geocodingResponse.getLongitude());
            }
        }
    }

    @And("^The error received is like")
    public void validateErrorContent(DataTable expectedValues) throws IOException {
        List<Map<String, String>> expected = expectedValues.asMaps(String.class, String.class);
        GeocodingErrorResponse errorResponse = RestConsumer.convertResponseToErrorModel(responseHolder);
        for (Map<String, String> map : expected) {
            if(map.containsKey(ERROR)){
                Assert.assertTrue(errorResponse.getError().startsWith(map.get(ERROR)));
            }
            if(map.containsKey(ERROR_DESCRIPTION)){
                Assert.assertTrue(errorResponse.getErrorDescription().startsWith(map.get(ERROR_DESCRIPTION)));
            }
            if(map.containsKey(REFERENCE)){
                Assert.assertNotNull(errorResponse.getReference());
                Assert.assertNotEquals("",errorResponse.getReference());
            }
        }
    }

    private String readFile(String filename) throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource(filename).toURI());
        StringBuilder data = new StringBuilder();
        Stream<String> lines = Files.lines(path);
        lines.forEach(line -> data.append(line).append("\n"));
        lines.close();
        return data.toString();
    }
}
