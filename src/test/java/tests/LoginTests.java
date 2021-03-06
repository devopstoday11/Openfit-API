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

public class LoginTests {
    SubscriptionsTests subscriptionsTestsForLogin = new SubscriptionsTests();
    FreeRegisterTests freeRegisterTests = new FreeRegisterTests();
    HelperMethods helperMethods = new HelperMethods();
    CheckerMethods checkerMethods = new CheckerMethods();

    @Test(groups = "hp")
    @Description("Login with subscribed user.")
    @Step("Login with valid credentials:")
    public void loginWithSubscribedUser() throws IOException, ParseException {
        subscriptionsTestsForLogin.registration6m();
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "login")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP");
        Response response = requestBuilder.post("{\"email\": \"" + helperMethods.userData.get("email") + "\",\"password\":\"Test1234@\"}", "application/json");
        System.out.println("\nRequest to login with subscribed  user: \n" + response.getCurl() + "\n\nResponse: \n" + response.body());
        checkerMethods.getLoginResponseContent(response);
        Allure.addAttachment("Request", response.getCurl());
        Allure.addAttachment("Response", response.body());
    }

    @Test(groups = "hp")
    @Description("Login with registered user.")
    @Step("Subscribe with valid product ID:, Login with valid credentials:")
    public void loginWithRegisteredUser() throws IOException, ParseException {
        freeRegisterTests.freeRegister();
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "login")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP");
        Response response = requestBuilder.post("{\"email\": \"" + helperMethods.userData.get("email") + "\",\"password\":\"Test1234@\"}", "application/json");
        System.out.println("\nRequest to login with free user: \n" + response.getCurl() + "\n\nResponse: \n" + response.body());
        checkerMethods.getLoginResponseContent(response);
        Allure.addAttachment("Request: ", response.getCurl());
        Allure.addAttachment("Response: ", response.body());
    }
}
