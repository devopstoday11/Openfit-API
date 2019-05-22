import io.qameta.allure.Description;
import io.qameta.allure.Step;
import net.minidev.json.parser.ParseException;
import org.testng.annotations.Test;
import requests.RequestBuilder;
import requests.Response;

import java.io.IOException;

public class Subscriptions {

    APITest apiTest = new APITest();

    @Test(priority = 1)
    @Description("Make request to subscribe with 6m.")
    @Step("Make request to generate valid ID Token: {0}, Make request to register free user: {1}, Make request to Subscribe with 6 months subscription plan: {2}")
    public void registration1m() throws IOException, ParseException {
        apiTest.getIDToken();
        apiTest.freeRegister();
        Methods.headers((String) Methods.userData.get("id_token"));
        RequestBuilder requestBuilder = new RequestBuilder("https://connect-checkout-be.qa.openfit.com");
        requestBuilder.addPathParameters("v1", "subscriptions", "new")
                .addHeader(Methods.headers);
        Response response = requestBuilder.post("{\"items\":[\n" +
                "\t{\n" +
                "\t\"product_id\":1685587853379,\n" +
                "\t\"variant_id\":15447916838979,\n" +
                "\t\"quantity\":1}],\n" +
                "\t\"token_id\":" + Methods.tokenIDs.get("token_id") + ",\n" +
                "\t\"access_token\":" + Methods.userData.get("access_token") + "\",\n" +
                "\t\"shipping_address\":\n" +
                "\t\t{\"first_name\":" + Methods.userData.get("given_name") + "\",\n" +
                "\t\t\"last_name\":" + Methods.userData.get("family_name") + "\",\n" +
                "\t\t\"state\":\"CA\",\n" +
                "\t\t\"city\":\"Santa Monica\",\n" +
                "\t\t\"address1\":\"3301 Exposition Blvd\",\n" +
                "\t\t\"zip\":\"90404-5045\"\n" +
                "\t\t},\"shipping_method\":\"on\"\n" +
                "\t\n" +
                "}");
        System.out.println("Registration 1m:\n" + response.getCurl());
        System.out.println("\nResponse: " + response.body());
    }

    @Test(priority = 1)
    @Description("Make request to subscribe with 6m.")
    @Step("Make request to generate valid ID Token: {0}, Make request to register free user: {1}, Make request to Subscribe with 6 months subscription plan: {2}")
    public void registration6m() throws IOException, ParseException {
        apiTest.getIDToken();
        apiTest.freeRegister();
        Methods.headers((String) Methods.userData.get("id_token"));
        RequestBuilder requestBuilder = new RequestBuilder("https://connect-checkout-be.qa.openfit.com");
        requestBuilder.addPathParameters("v1", "subscriptions", "new")
                .addHeader(Methods.headers);
        Response response = requestBuilder.post("{\"items\":[\n" +
                "\t{\n" +
                "\t\"product_id\":1685587853379,\n" +
                "\t\"variant_id\":15447916838979,\n" +
                "\t\"quantity\":1}],\n" +
                "\t\"token_id\":"+ Methods.tokenIDs.get("token_id")+",\n" +
                "\t\"access_token\":"+ Methods.userData.get("access_token")+"\",\n" +
                "\t\"shipping_address\":\n" +
                "\t\t{\"first_name\":"+ Methods.userData.get("given_name")+"\",\n" +
                "\t\t\"last_name\":"+ Methods.userData.get("family_name")+"\",\n" +
                "\t\t\"state\":\"CA\",\n" +
                "\t\t\"city\":\"Santa Monica\",\n" +
                "\t\t\"address1\":\"3301 Exposition Blvd\",\n" +
                "\t\t\"zip\":\"90404-5045\"\n" +
                "\t\t},\"shipping_method\":\"on\"\n" +
                "\t\n" +
                "}");
        System.out.println("Registration 1m:\n"+response.getCurl());
        System.out.println("\nResponse: " +response.body());
    }

    @Test(priority = 1)
    @Description("Make request to subscribe with 12m.")
    @Step("Make request to generate valid ID Token: {0}, Make request to register free user: {1}, Make request to Subscribe with 12 months subscription plan: {2}")
    public void registration12m() throws IOException, ParseException {
        apiTest.getIDToken();
        apiTest.freeRegister();
        Methods.headers((String) Methods.userData.get("id_token"));
        RequestBuilder requestBuilder = new RequestBuilder("https://connect-checkout-be.qa.openfit.com");
        requestBuilder.addPathParameters("v1", "subscriptions", "new")
                .addHeader(Methods.headers);
        Response response = requestBuilder.post("{\"items\":[\n" +
                "\t{\n" +
                "\t\"product_id\":1685587853379,\n" +
                "\t\"variant_id\":15447916838979,\n" +
                "\t\"quantity\":1}],\n" +
                "\t\"token_id\":"+ Methods.tokenIDs.get("token_id")+",\n" +
                "\t\"access_token\":"+ Methods.userData.get("access_token")+"\",\n" +
                "\t\"shipping_address\":\n" +
                "\t\t{\"first_name\":"+ Methods.userData.get("given_name")+"\",\n" +
                "\t\t\"last_name\":"+ Methods.userData.get("family_name")+"\",\n" +
                "\t\t\"state\":\"CA\",\n" +
                "\t\t\"city\":\"Santa Monica\",\n" +
                "\t\t\"address1\":\"3301 Exposition Blvd\",\n" +
                "\t\t\"zip\":\"90404-5045\"\n" +
                "\t\t},\"shipping_method\":\"on\"\n" +
                "\t\n" +
                "}");
        System.out.println("Registration 1m:\n"+response.getCurl());
        System.out.println("\nResponse: " +response.body());
    }

    @Test(priority = 0)
    @Description( "GET Subscription after Subscribing.")
    @Step("Free Registration:, Generated Token ID:, Subscribe with valid product ID:")
    public void getSubscriptions() throws IOException, ParseException {
        registration6m();
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
}
