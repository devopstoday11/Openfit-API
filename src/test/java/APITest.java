import io.qameta.allure.Description;
import io.qameta.allure.Step;
import net.minidev.json.parser.ParseException;
import org.testng.annotations.Test;
import requests.RequestBuilder;
import requests.Response;

import java.io.IOException;


public class APITest {
    SubscriptionsTests subscriptions = new SubscriptionsTests();


    @Test(priority = 0)
    @Description("Make request to GET token ID.")
    @Step("Make request to generate valid ID Token:")
    public void getIDToken() throws IOException, ParseException {
        RequestBuilder requestBuilder = new RequestBuilder("https://api.recurly.com");
        requestBuilder.addPathParameters("js","v1","token");
        Response response = requestBuilder.post("{\"first_name\": \"Instigate\", \"last_name\": \"Mobile\", \"postal_code\": \"90404\", \"country\": \"US\", \"number\": \"4111111111111111\",\"month\": \"12\", \"year\": \"2020\", \"cvv\": \"132\", \"key\": \"ewr1-bBXWWGAuZMiIT4CL76bvwL\" }","application/json");
        Methods.getTokenId(response);
    }

    @Test(priority = 0)
    @Description("Free register with valid credentials.")
    @Step("Make request to create free user:")
    public void freeRegister() throws IOException, ParseException {
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "register")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP");
        Response response = requestBuilder.post("{\n" +
                "        \"given_name\":\"Test\",\n" +
                "        \"family_name\":\"Test\",\n" +
                "        \"email\":\"tester" + Methods.randomAlphaNumeric(5) + "@yopmail.com\",\n" +
                "        \"password\":\"Test1234@\"\n" +
                "        \n" +
                "}", "application/json");
        Methods.getUser(response);

        System.out.println("Free register:\n"+response.getCurl());
        System.out.println("\nResponse: " +response.body());
    }

    @Test(priority = 0)
    @Description( "GET Subscription after Subscribing.")
    @Step("Free Registration:, Generated Token ID:, Subscribe with valid product ID:")
    public void getSubscriptions() throws IOException, ParseException {
        subscriptions.registration6m();
        String authorization = (String) Methods.headers.get("Authorization");
        RequestBuilder requestBuilder = new RequestBuilder("https://connect-account-be.qa.openfit.com");
        requestBuilder.addPathParameters("v1", "subscriptions")
                .addHeader("x-api-key", "ZUL7kSands2gzEHb8ZB7X1onCufpTkqJ3S3h5AlK")
                .addHeader("Authorization", authorization);
        Response response = requestBuilder.get();
        System.out.println("GET Subscriptions:\n"+response.getCurl());
        System.out.println("\nResponse: " +response.body());
        Methods.getSubscriptionContent(response);
    }

    @Test(priority = 0)
    @Description("Change subscribed user attributes.")
    @Step("Make request to create free user: , Make request to generate valid token ID:, Make request to subscribe with valid credentials:, Make reqeust to change user attributes:")
    public void changeAttributesForSubscribedUser() throws IOException, ParseException {
        subscriptions.registration6m();
        String autorization = (String) Methods.headers.get("Authorization");
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "changeattributes")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP")
                .addHeader("Authorization", autorization);
        Response response = requestBuilder.post("{\n" +
                "        \"email\":"+Methods.userData.get("email")+"\",\n" +
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
       subscriptions.registration6m();
        String autorization = (String) Methods.headers.get("Authorization");
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "changeattributes")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP")
                .addHeader("Authorization", autorization);
        Response response = requestBuilder.post("{\n" +
                "        \"email\":"+Methods.userData.get("email")+"\",\n" +
                "        \"first_name\":\"Updated\",\n" +
                "        \"last_name\":\"Name\"\n" +
                "        \n" +
                "}", "application/json");

        System.out.println(response.getCurl());
        System.out.println(response.body());
    }

    @Test(priority = 0)
    @Description( "Change password for Registered User.")
    @Step("Make request to create free user:, Make request to change user password:")
    public void changePasswordForRegisteredUser() throws IOException, ParseException {
        freeRegister();
        String authorization = (String) Methods.headers.get("Authorization");
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "changepassword")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP")
                .addHeader("Authorization", authorization)
                .addHeader("Content-Type", "application/json");
        Response response = requestBuilder.put("{\"previousPassword\":\"Test1234@\",\"proposedPassword\":\"Test1234!\"}");
        System.out.println(response.getCurl());
        System.out.println(response.body());
    }


    @Test(priority = 0)
    @Description("Change password for Subscribed User.")
    @Step("Make request to create free user:, Make request to generate valid token ID:, Make request to subscribe with valid credentials:, Make request to change user password:")
    public void changePasswordForSubscribedUser() throws IOException, ParseException {
        subscriptions.registration6m();
        String authorization = (String) Methods.headers.get("Authorization");
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "changepassword")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP")
                .addHeader("Authorization", authorization)
                .addHeader("Content-Type", "application/json");
        Response response = requestBuilder.put("{\"previousPassword\":\"Test1234@\",\"proposedPassword\":\"Test1234!\"}");
        System.out.println(response.getCurl());
        System.out.println(response.body());
    }

//    @Test
//    public void ProgramMaterialStaticData() throws IOException, JSONException {
//        Methods.doRequestAndCompare("/home/arsen_d/Desktop/OpenfitAPIs/src/test/programsTest.txt");
//    }



}

