import io.qameta.allure.Description;
import io.qameta.allure.Step;
import net.minidev.json.parser.ParseException;
import org.testng.annotations.Test;
import requests.RequestBuilder;
import requests.Response;
import util.Checker;
import util.HelperMethods;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FreeRegisterTests {
    FileReader fileReader = new FileReader("/home/arsen_d/Desktop/Openfit-API/OpenfitAPIs/environments/qa.properties");
    static String x_api_key;
    static String freeRegisterUrl;
    String email = "tester" + HelperMethods.randomAlphaNumeric(5) + "@yopmail.com";
    static String content_type;

    public FreeRegisterTests() throws IOException {
        Properties properties = new Properties();
        properties.load(fileReader);
        x_api_key = properties.getProperty("api.qa.x-api-key");
        freeRegisterUrl = properties.getProperty("api.freeRegister.server");
        content_type = properties.getProperty("api.contentType");
    }

    @Test
    @Description("Free register with valid credentials.")
    @Step("Make request to create free user:")
    public void freeRegister() throws IOException, ParseException {
        RequestBuilder requestBuilder = new RequestBuilder(freeRegisterUrl)
                .addPathParameters("v1", "register")
                .addHeader("x-api-key", x_api_key);
        Response response = requestBuilder.post("{\n" +
                "        \"given_name\":\"Test\",\n" +
                "        \"family_name\":\"Test\",\n" +
                "        \"email\":\""+email+"\",\n" +
                "        \"password\":\"Test1234@\"\n" +
                "        \n" +
                "}", content_type);
        HelperMethods.getUser(response);
        Checker.getFreeRegisterContent(response);
        System.out.println("Request to create free user: \n"+response.getCurl()+"\n\nResponse: \n"+response.body());
    }
}
