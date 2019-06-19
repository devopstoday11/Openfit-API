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

import java.io.IOException;

public class ChangeAttributesTests {
    SubscriptionsTests subscriptionsTests = new SubscriptionsTests();
    FreeRegisterTests freeRegisterTests = new FreeRegisterTests();
    HelperMethods helperMethods = new HelperMethods();
    CheckerMethods checkerMethods = new CheckerMethods();

    public ChangeAttributesTests() {
    }

    @Test(groups = "major")
    @Description("Change subscribed user attributes.")
    @Step("Make request to create free user: , Make request to generate valid token ID:, Make request to subscribe with valid credentials:, Make reqeust to change user attributes:")
    public void changeAttributesForSubscribedUser() throws IOException, ParseException {
        subscriptionsTests.registration6m();
        String authorization = "Bearer " + helperMethods.userData.get("access_token");
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "changeattributes")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP")
                .addHeader("Authorization", authorization);
        Response response = requestBuilder.post("{\n" +
                "        \"email\":\"" + helperMethods.userData.get("email") + "\",\n" +
                "        \"first_name\":\"Updated\",\n" +
                "        \"last_name\":\"Name\"\n" +
                "        \n" +
                "}", "application/json");
        System.out.println("\nRequest to change attributes for subscribed user: \n" + response.getCurl() + "\n\nResponse: \n" + response.body());
        checkerMethods.getChangeAttributesResponseContent(response);
        Allure.addAttachment("Request: ", response.getCurl());
        Allure.addAttachment("Response: ", response.body());
    }

    @Test(groups = "major")
    @Description("Change registered user attributes.")
    @Step("Make request to create free user:, Make request to generate valid token ID:, Make request to subscribe with valid credentials:, Make reqeust to change user attributes:")
    public void changeAttributesForRegisteredUser() throws IOException, ParseException {
        freeRegisterTests.freeRegister();
        HelperMethods.headers((String) helperMethods.userData.get("id_token"));
        String authorization = "Bearer " + helperMethods.userData.get("access_token");
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "changeattributes")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP")
                .addHeader("Authorization", authorization);
        System.out.println();
        Response response = requestBuilder.post("{\n" +
                "        \"email\":\"" + helperMethods.userData.get("email") + "\",\n" +
                "        \"first_name\":\"Updated\",\n" +
                "        \"last_name\":\"Name\"\n" +
                "        \n" +
                "}", "application/json");
        System.out.println("\nRequest to change attributes for registered user: \n" + response.getCurl() + "\n\nResponse: \n" + response.body());
        checkerMethods.getChangeAttributesResponseContent(response);
        Allure.addAttachment("Request: ", response.getCurl());
        Allure.addAttachment("Response: ", response.body());
    }
}
