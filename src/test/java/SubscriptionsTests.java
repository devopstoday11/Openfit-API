import io.qameta.allure.Description;
import io.qameta.allure.Step;
import net.minidev.json.parser.ParseException;
import org.testng.annotations.Test;
import requests.RequestBuilder;
import requests.Response;
import util.HelperMethods;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SubscriptionsTests {
    FreeRegisterTests freeRegisterTests;
    GetTokenIdTests getTokenIdTests;

    FileReader fileReader;
    static String url;
    static String product_id;
    static String variant_id_1m;
    static String variant_id_6m;
    static String variant_id_12m;
    {
        try {
            fileReader = new FileReader("/home/arsen_d/Desktop/Openfit-API/OpenfitAPIs/environments/qa.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public SubscriptionsTests(){
        Properties properties = new Properties();
        try {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        product_id = properties.getProperty("api.product_id");
        variant_id_1m = properties.getProperty("api.1m.variant_id");
        variant_id_6m = properties.getProperty("api.6m.variant_id");
        variant_id_12m = properties.getProperty("api.12m.variant_id");
        url = properties.getProperty("api.Subscription.server");
    }

    @Test(priority = 1)
    @Description("Make request to subscribe with 1m.")
    @Step("Make request to Subscribe with 1 month subscription plan:")
    public void registration1m() throws IOException, ParseException {
        getTokenIdTests.getIDToken();
        freeRegisterTests.freeRegister();
        HelperMethods.headers((String) HelperMethods.userData.get("id_token"));
        RequestBuilder requestBuilder = new RequestBuilder(url);
        requestBuilder.addPathParameters("v1", "subscriptionsTests", "new")
                .addHeader(HelperMethods.headers);
        Response response = requestBuilder.post("{\"items\":[\n" +
                "\t{\n" +
                "\t\"product_id\":"+product_id+",\n" +
                "\t\"variant_id\":"+variant_id_1m+",\n" +
                "\t\"quantity\":1}],\n" +
                "\t\"token_id\":" + HelperMethods.tokenIDs.get("token_id") + ",\n" +
                "\t\"access_token\":" + HelperMethods.userData.get("access_token") + "\",\n" +
                "}");
        System.out.println("Registration 1m:\n" + response.getCurl());
        System.out.println("\nResponse: " + response.body());
    }

    @Test(priority = 1)
    @Description("Make request to subscribe with 6m.")
    @Step("Make request to generate valid ID Token: {0}, Make request to register free user: {1}, Make request to Subscribe with 6 months subscription plan: {2}")
    public void registration6m() throws IOException, ParseException {
        getTokenIdTests.getIDToken();
        freeRegisterTests.freeRegister();
        HelperMethods.headers((String) HelperMethods.userData.get("id_token"));
        RequestBuilder requestBuilder = new RequestBuilder(url);
        requestBuilder.addPathParameters("v1", "subscriptionsTests", "new")
                .addHeader(HelperMethods.headers);
        Response response = requestBuilder.post("{\"items\":[\n" +
                "\t{\n" +
                "\t\"product_id\":"+product_id+",\n" +
                "\t\"variant_id\":"+variant_id_6m+",\n" +
                "\t\"quantity\":1}],\n" +
                "\t\"token_id\":"+ HelperMethods.tokenIDs.get("token_id")+",\n" +
                "\t\"access_token\":"+ HelperMethods.userData.get("access_token")+"\",\n" +
                "}");
        System.out.println("Registration 1m:\n"+response.getCurl());
        System.out.println("\nResponse: " +response.body());

    }

    @Test(priority = 1)
    @Description("Make request to subscribe with 12m.")
    @Step("Make request to generate valid ID Token: {0}, Make request to register free user: {1}, Make request to Subscribe with 12 months subscription plan: {2}")
    public void registration12m() throws IOException, ParseException {
        getTokenIdTests.getIDToken();
        freeRegisterTests.freeRegister();
        HelperMethods.headers((String) HelperMethods.userData.get("id_token"));
        RequestBuilder requestBuilder = new RequestBuilder(url);
        requestBuilder.addPathParameters("v1", "subscriptionsTests", "new")
                .addHeader(HelperMethods.headers);
        Response response = requestBuilder.post("{\"items\":[\n" +
                "\t{\n" +
                "\t\"product_id\":"+product_id+",\n" +
                "\t\"variant_id\":"+variant_id_12m+",\n" +
                "\t\"quantity\":1}],\n" +
                "\t\"token_id\":"+ HelperMethods.tokenIDs.get("token_id")+",\n" +
                "\t\"access_token\":"+ HelperMethods.userData.get("access_token")+"\",\n" +"}");
        System.out.println("Registration 1m:\n"+response.getCurl());
        System.out.println("\nResponse: \n" +response.body());
    }
}
