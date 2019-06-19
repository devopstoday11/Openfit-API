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

public class ChangePasswordTests {
    SubscriptionsTests subscriptionsTests = new SubscriptionsTests();
    FreeRegisterTests freeRegisterTests = new FreeRegisterTests();
    HelperMethods helperMethods = new HelperMethods();
    CheckerMethods checkerMethods = new CheckerMethods();

    public ChangePasswordTests() {
    }

    @Test(groups = "major")
    @Description("Change password for Registered User.")
    @Step("Make request to create free user:, Make request to change user password:")
    public void changePasswordForRegisteredUser() throws IOException, ParseException {
        freeRegisterTests.freeRegister();
        HelperMethods.headers((String) helperMethods.userData.get("id_token"));
        String authorization = "Bearer " + helperMethods.userData.get("access_token");
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "changepassword")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP")
                .addHeader("Authorization", authorization)
                .addHeader("Content-Type", "application/json");
        Response response = requestBuilder.post("{\"previousPassword\":\"Test1234@\",\"proposedPassword\":\"Test1234!\"}");
        System.out.println("\nRequest to change password for registered user: \n" + response.getCurl() + "\n\nResponse: \n" + response.body());
        checkerMethods.getChangePasswordResponseContent(response);
        Allure.addAttachment("Request: ", response.getCurl());
        Allure.addAttachment("Response: ", response.body());
    }


    @Test(groups = "major")
    @Description("Change password for Subscribed User.")
    @Step("Make request to change subscribed user password:")
    public void changePasswordForSubscribedUser() throws IOException, ParseException {
        subscriptionsTests.registration6m();
        String authorization = "Bearer " + helperMethods.userData.get("access_token");
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "changepassword")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP")
                .addHeader("Authorization", authorization)
                .addHeader("Content-Type", "application/json");
        Response response = requestBuilder.post("{\"previousPassword\":\"Test1234@\",\"proposedPassword\":\"Test1234!\"}");
        System.out.println("\nRequest to change password for subscribed user: \n" + response.getCurl() + "\n\nResponse: \n" + response.body());
        checkerMethods.getChangePasswordResponseContent(response);
        Allure.addAttachment("Request: ", response.getCurl());
        Allure.addAttachment("Response: ", response.body());
    }
}
