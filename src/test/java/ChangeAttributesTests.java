import io.qameta.allure.Description;
import io.qameta.allure.Step;
import net.minidev.json.parser.ParseException;
import org.testng.annotations.Test;
import requests.RequestBuilder;
import requests.Response;
import util.HelperMethods;

import java.io.IOException;

public class ChangeAttributesTests {
    SubscriptionsTests subscriptionsTests = new SubscriptionsTests();

    public ChangeAttributesTests() throws IOException {
    }

    @Test(priority = 0)
    @Description("Change subscribed user attributes.")
    @Step("Make request to create free user: , Make request to generate valid token ID:, Make request to subscribe with valid credentials:, Make reqeust to change user attributes:")
    public void changeAttributesForSubscribedUser() throws IOException, ParseException {
        subscriptionsTests.registration6m();
        String autorization = (String) HelperMethods.headers.get("Authorization");
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "changeattributes")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP")
                .addHeader("Authorization", autorization);
        Response response = requestBuilder.post("{\n" +
                "        \"email\":"+ HelperMethods.userData.get("email")+"\",\n" +
                "        \"first_name\":\"Updated\",\n" +
                "        \"last_name\":\"Name\"\n" +
                "        \n" +
                "}", "application/json");

        System.out.println(response.getCurl());
        System.out.println(response.body());
    }

    @Test(priority = 0)
    @Description("Change registered user attributes.")
    @Step("Make request to create free user:, Make request to generate valid token ID:, Make request to subscribe with valid credentials:, Make reqeust to change user attributes:")
    public void changeAttributesForRegisteredUser() throws IOException, ParseException {
        FreeRegisterTests.freeRegister();
        HelperMethods.headers((String) HelperMethods.userData.get("id_token"));
        String authorization = (String) HelperMethods.headers.get("Authorization");
        System.out.println(authorization);
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "changeattributes")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP")
                .addHeader("Authorization", authorization);
        System.out.println();
        Response response = requestBuilder.post("{\n" +
                "        \"email\":\" "+ HelperMethods.userData.get("email")+"\",\n" +
                "        \"first_name\":\"Updated\",\n" +
                "        \"last_name\":\"Name\"\n" +
                "        \n" +
                "}", "application/json");

        System.out.println(response.getCurl());
        System.out.println(response.body());
    }
}