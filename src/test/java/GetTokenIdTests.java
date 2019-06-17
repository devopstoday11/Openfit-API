import io.qameta.allure.Description;
import io.qameta.allure.Step;
import net.minidev.json.parser.ParseException;
import org.testng.annotations.Test;
import requests.RequestBuilder;
import requests.Response;
import util.CheckerMethods;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class GetTokenIdTests {
    FileReader fileReader;
    {
        try {
            fileReader = new FileReader("/home/arsen_d/Desktop/Openfit-API/OpenfitAPIs/environments/qa.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static String getIdTokenUrl;

    public GetTokenIdTests() throws IOException{
        Properties properties = new Properties();
        properties.load(fileReader);
        getIdTokenUrl = properties.getProperty("api.TokenId.server");
    }

    @Test
    @Description("Make request to GET token ID.")
    @Step("Make request to generate valid ID Token:")
    public void getIDToken() throws IOException, ParseException {
        RequestBuilder requestBuilder = new RequestBuilder(getIdTokenUrl);
        requestBuilder.addPathParameters("js","v1","token");
        Response response = requestBuilder.post("{\"first_name\": \"Instigate\", \"last_name\": \"Mobile\", \"postal_code\": \"90404\", \"country\": \"US\", \"number\": \"4111111111111111\",\"month\": \"12\", \"year\": \"2020\", \"cvv\": \"132\", \"key\": \"ewr1-bBXWWGAuZMiIT4CL76bvwL\" }","application/json");
        CheckerMethods.getTokenIdResponseContent(response);
        System.out.println("\nRequest to get ID token: \n"+response.getCurl()+"\n\nResponse: \n"+response.body());

    }
}

