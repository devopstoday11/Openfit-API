import io.qameta.allure.Description;
import io.qameta.allure.Step;
import net.minidev.json.parser.ParseException;
import org.testng.annotations.Test;
import requests.RequestBuilder;
import requests.Response;

import java.io.IOException;


public class APITest {
    Subscriptions subscriptions;


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

//    @Test
//    public void ProgramMaterialStaticData() throws IOException, JSONException {
//        Methods.doRequestAndCompare("/home/arsen_d/Desktop/OpenfitAPIs/src/test/programsTest.txt");
//    }



}

