import io.qameta.allure.Description;
import io.qameta.allure.Step;
import net.minidev.json.parser.ParseException;
import org.testng.annotations.Test;
import requests.RequestBuilder;
import requests.Response;
import util.HelperMethods;

import java.io.IOException;

public class LoginTests {
    SubscriptionsTests subscriptionsTestsForLogin = new SubscriptionsTests();
    APITests apiTests = new APITests();

    @Test(priority = 0)
    @Description( "Login with subscribed user.")
    @Step("Subscribe with valid product ID: {0}, Login with valid credentials: {1}")
    public void loginWithSubscribedUser() throws IOException, ParseException {
        subscriptionsTestsForLogin.registration6m();
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "login")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP");
        Response response = requestBuilder.post("{\"email\": \""+ HelperMethods.userData.get("email")+"\",\"password\":\"Test1234@\"}", "application/json");
        HelperMethods.getLoginResponseContent(response);
        System.out.println(response.getCurl());
        System.out.println(response.body());

    }

    @Test(priority =0)
    @Description("Login with registered user.")
    @Step("Subscribe with valid product ID:, Login with valid credentials:")
    public void loginWithRegisteredUser() throws IOException, ParseException {
        apiTests.freeRegister();
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "login")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP");
        Response response = requestBuilder.post("{\"email\": \""+ HelperMethods.userData.get("email")+"\",\"password\":\"Test1234@\"}", "application/json");
        HelperMethods.getLoginResponseContent(response);
        System.out.println(response.getCurl());
        System.out.println(response.body());
    }
}
