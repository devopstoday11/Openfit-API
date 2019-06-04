import io.qameta.allure.Description;
import io.qameta.allure.Step;
import net.minidev.json.parser.ParseException;
import org.testng.annotations.Test;
import requests.RequestBuilder;
import requests.Response;
import util.HelperMethods;

import java.io.IOException;

public class ChangePasswordTests {
    SubscriptionsTests subscriptionsTests = new SubscriptionsTests();

    public ChangePasswordTests() throws IOException {
    }

    @Test
    @Description( "Change password for Registered User.")
    @Step("Make request to create free user:, Make request to change user password:")
    public void changePasswordForRegisteredUser() throws IOException, ParseException {
        FreeRegisterTests.freeRegister();
        HelperMethods.headers((String) HelperMethods.userData.get("id_token"));
        String authorization = (String) HelperMethods.headers.get("Authorization");
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "changepassword")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP")
                .addHeader("Authorization", authorization)
                .addHeader("Content-Type", "application/json");
        Response response = requestBuilder.put("{\"previousPassword\":\"Test1234@\",\"proposedPassword\":\"Test1234!\"}");
        System.out.println(response.getCurl());
        System.out.println(response.body());
    }


    @Test
    @Description("Change password for Subscribed User.")
    @Step("Make request to create free user:, Make request to generate valid token ID:, Make request to subscribe with valid credentials:, Make request to change user password:")
    public void changePasswordForSubscribedUser() throws IOException, ParseException {
        subscriptionsTests.registration6m();
        String authorization = (String) HelperMethods.headers.get("Authorization");
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "changepassword")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP")
                .addHeader("Authorization", authorization)
                .addHeader("Content-Type", "application/json");
        Response response = requestBuilder.put("{\"previousPassword\":\"Test1234@\",\"proposedPassword\":\"Test1234!\"}");
        System.out.println(response.getCurl());
        System.out.println(response.body());
    }
}