import io.qameta.allure.Description;
import io.qameta.allure.Step;
import net.minidev.json.parser.ParseException;
import org.testng.annotations.Test;
import requests.RequestBuilder;
import requests.Response;

import java.io.IOException;

public class LoginTests {
    Subscriptions subscriptionsForLogin = new Subscriptions();
    APITest apiTests = new APITest();

    @Test(priority = 0)
    @Description( "Login with subscribed user.")
    @Step("Subscribe with valid product ID: {0}, Login with valid credentials: {1}")
    public void loginWithSubscribedUser() throws IOException, ParseException {
        subscriptionsForLogin.registration6m();
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "login")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP");
        Response response = requestBuilder.post("{\"email\": \""+ Methods.userData.get("email")+"\",\"password\":\"Test1234@\"}", "application/json");
        Methods.getLoginResponseContent(response);
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
        Response response = requestBuilder.post("{\"email\": \""+ Methods.userData.get("email")+"\",\"password\":\"Test1234@\"}", "application/json");
        Methods.getLoginResponseContent(response);
        System.out.println(response.getCurl());
        System.out.println(response.body());
    }
}
