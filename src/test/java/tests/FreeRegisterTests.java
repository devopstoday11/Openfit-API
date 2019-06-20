package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import net.minidev.json.parser.ParseException;
import org.testng.annotations.Test;
import requests.RequestBuilder;
import requests.Response;
import util.CheckerMethods;
import util.HelperMethods;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FreeRegisterTests {
    FileReader fileReader;

    {
        try {
            fileReader = new FileReader("/home/arsen_d/Desktop/Openfit-API/OpenfitAPIs/environments/qa.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    HelperMethods helperMethods = new HelperMethods();
    CheckerMethods checkerMethods = new CheckerMethods();

    static String x_api_key;
    static String freeRegisterUrl;
    static String content_type;

    public FreeRegisterTests() {
        Properties properties = new Properties();
        try {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        x_api_key = properties.getProperty("api.qa.x-api-key");
        freeRegisterUrl = properties.getProperty("api.freeRegister.server");
        content_type = properties.getProperty("api.contentType");
    }

    public String getRandomEmail () {
        return "tester" + HelperMethods.randomAlphaNumeric(5) + "@yopmail.com";
    }

    @Test(groups = "critical")
    @Description("Free register with valid credentials.")
    @Step("Make request to create free user:")
    public void freeRegister(){
        RequestBuilder requestBuilder = new RequestBuilder(freeRegisterUrl)
                .addPathParameters("v1", "register")
                .addHeader("x-api-key", x_api_key);
        Response response = null;
        try {
            response = requestBuilder.post("{\n" +
                    "        \"given_name\":\"Test\",\n" +
                    "        \"family_name\":\"Test\",\n" +
                    "        \"email\":\"" + getRandomEmail() + "\",\n" +
                    "        \"password\":\"Test1234@\"\n" +
                    "        \n" +
                    "}", content_type);
        } catch (IOException e) {
            System.out.println("Free Register IO Exception");
            e.printStackTrace();
        }
        try {
            helperMethods.getUser(response);
        } catch (ParseException e) {
            System.out.println("Free Register Parse Exception");
            e.printStackTrace();
        }
        System.out.println("Request to create free user: \n" + response.getCurl() + "\n\nResponse: \n" + response.body());
        checkerMethods.getFreeRegisterResponseContent(response);
        Allure.addAttachment("Request: ", response.getCurl());
        Allure.addAttachment("Response: ", response.body());
    }
}
