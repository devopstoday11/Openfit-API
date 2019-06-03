import io.qameta.allure.Description;
import io.qameta.allure.Step;
import net.minidev.json.parser.ParseException;
import org.testng.annotations.Test;
import requests.RequestBuilder;
import requests.Response;
import util.HelperMethods;

import java.io.IOException;

public class SubscriptionsTests {

    APITests apiTests = new APITests();

    @Test(priority = 1)
    @Description("Make request to subscribe with 6m.")
    @Step("Make request to generate valid ID Token: {0}, Make request to register free user: {1}, Make request to Subscribe with 6 months subscription plan: {2}")
    public void registration1m() throws IOException, ParseException {
        apiTests.getIDToken();
        apiTests.freeRegister();
        HelperMethods.headers((String) HelperMethods.userData.get("id_token"));
        RequestBuilder requestBuilder = new RequestBuilder("https://connect-checkout-be.qa.openfit.com");
        requestBuilder.addPathParameters("v1", "subscriptionsTests", "new")
                .addHeader(HelperMethods.headers);
        Response response = requestBuilder.post("{\"items\":[\n" +
                "\t{\n" +
                "\t\"product_id\":1685587853379,\n" +
                "\t\"variant_id\":15447916904515,\n" +
                "\t\"quantity\":1}],\n" +
                "\t\"token_id\":" + HelperMethods.tokenIDs.get("token_id") + ",\n" +
                "\t\"access_token\":" + HelperMethods.userData.get("access_token") + "\",\n" +
                "\t\"shipping_address\":\n" +
                "\t\t{\"first_name\":" + HelperMethods.userData.get("given_name") + "\",\n" +
                "\t\t\"last_name\":" + HelperMethods.userData.get("family_name") + "\",\n" +
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
        apiTests.getIDToken();
        apiTests.freeRegister();
        HelperMethods.headers((String) HelperMethods.userData.get("id_token"));
        RequestBuilder requestBuilder = new RequestBuilder("https://connect-checkout-be.qa.openfit.com");
        requestBuilder.addPathParameters("v1", "subscriptionsTests", "new")
                .addHeader(HelperMethods.headers);
        Response response = requestBuilder.post("{\"items\":[\n" +
                "\t{\n" +
                "\t\"product_id\":1685587853379,\n" +
                "\t\"variant_id\":15447916838979,\n" +
                "\t\"quantity\":1}],\n" +
                "\t\"token_id\":"+ HelperMethods.tokenIDs.get("token_id")+",\n" +
                "\t\"access_token\":"+ HelperMethods.userData.get("access_token")+"\",\n" +
                "\t\"shipping_address\":\n" +
                "\t\t{\"first_name\":"+ HelperMethods.userData.get("given_name")+"\",\n" +
                "\t\t\"last_name\":"+ HelperMethods.userData.get("family_name")+"\",\n" +
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
        apiTests.getIDToken();
        apiTests.freeRegister();
        HelperMethods.headers((String) HelperMethods.userData.get("id_token"));
        RequestBuilder requestBuilder = new RequestBuilder("https://connect-checkout-be.qa.openfit.com");
        requestBuilder.addPathParameters("v1", "subscriptionsTests", "new")
                .addHeader(HelperMethods.headers);
        Response response = requestBuilder.post("{\"items\":[\n" +
                "\t{\n" +
                "\t\"product_id\":1685587853379,\n" +
                "\t\"variant_id\":15447916937283,\n" +
                "\t\"quantity\":1}],\n" +
                "\t\"token_id\":"+ HelperMethods.tokenIDs.get("token_id")+",\n" +
                "\t\"access_token\":"+ HelperMethods.userData.get("access_token")+"\",\n" +
                "\t\"shipping_address\":\n" +
                "\t\t{\"first_name\":"+ HelperMethods.userData.get("given_name")+"\",\n" +
                "\t\t\"last_name\":"+ HelperMethods.userData.get("family_name")+"\",\n" +
                "\t\t\"state\":\"CA\",\n" +
                "\t\t\"city\":\"Santa Monica\",\n" +
                "\t\t\"address1\":\"3301 Exposition Blvd\",\n" +
                "\t\t\"zip\":\"90404-5045\"\n" +
                "\t\t},\"shipping_method\":\"on\"\n" +
                "\t\n" +
                "}");
        System.out.println("Registration 1m:\n"+response.getCurl());
        System.out.println("\nResponse: \n" +response.body());
    }

    @Test(priority = 0)
    @Description( "GET Subscription after Subscribing.")
    @Step("Free Registration:, Generated Token ID:, Subscribe with valid product ID:")
    public void getSubscriptions() throws IOException, ParseException {

    }
}
