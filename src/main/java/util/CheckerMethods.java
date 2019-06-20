package util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.testng.asserts.SoftAssert;
import requests.Response;

import java.io.File;

public class CheckerMethods {
    static SoftAssert softAssert = new SoftAssert();
    HelperMethods helperMethods = new HelperMethods();

    public void getTokenIdResponseContent(Response response) {
        String idToken = JsonPath.read(response.body(), "id");
        String type = JsonPath.read(response.body(), "type");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(idToken);
        softAssert.assertNotNull(type);
        softAssert.assertEquals( "credit_card", type);
        helperMethods.tokenIDs.put("token_id", "\"" + idToken + "\"");

    }

    public void getFreeRegisterResponseContent(Response response) {
        String email = JsonPath.read(response.body(), "email");
        String given_name = JsonPath.read(response.body(), "given_name");
        String family_name = JsonPath.read(response.body(), "family_name");
        String sub = JsonPath.read(response.body(), "sub");
        String id_token = JsonPath.read(response.body(), "id_token");
        String access_token = JsonPath.read(response.body(), "access_token");
        String refresh_token = JsonPath.read(response.body(), "refresh_token");
        softAssert.assertEquals( "Test", given_name);
        softAssert.assertEquals("Test", family_name);
        softAssert.assertNotNull(email);
        softAssert.assertNotNull(sub);
        softAssert.assertNotNull(id_token);
        softAssert.assertNotNull(access_token);
        softAssert.assertNotNull(refresh_token);
        softAssert.assertEquals(helperMethods.userData.get("email"), email);
        softAssert.assertAll();
    }

    public void getChangeAttributesResponseContent(Response response) {
        JSONObject getChangeAttributesResponseContent = null;
        try {
            getChangeAttributesResponseContent = (JSONObject) new JSONParser().parse(response.body());
        } catch (ParseException e) {
            System.out.println("getChangeAttributesResponseContent exception");
            e.printStackTrace();
        }
        String success = getChangeAttributesResponseContent.getAsString("success");
        softAssert.assertNotNull(success);
        softAssert.assertEquals(success, "true");
        softAssert.assertAll();
    }

    public void getChangePasswordResponseContent(Response response) {
        JSONObject getChangePasswordResponseContent = null;
        try {
            getChangePasswordResponseContent = (JSONObject) new JSONParser().parse(response.body());
        } catch (ParseException e) {
            System.out.println("getChangePasswordResponseContent exception");
            e.printStackTrace();
        }
        String success = getChangePasswordResponseContent.getAsString("success");
        softAssert.assertNotNull(success);
        softAssert.assertEquals(success, "true");
        softAssert.assertAll();
    }

    public void getSubscriptionResponseContent(Response response) {
        String result = JsonPath.read(response.body(), "result");
        String name_on_account = JsonPath.read(response.body(), "$.details.address.name_on_account");
        String first_name = JsonPath.read(response.body(), "$.details.address.first_name");
        String last_name = JsonPath.read(response.body(), "$.details.address.last_name");
        String company = JsonPath.read(response.body(), "$.details.address.company");
        String address1 = JsonPath.read(response.body(), "$.details.address.address1");
        String address2 = JsonPath.read(response.body(), "$.details.address.address2");
        String city = JsonPath.read(response.body(), "$.details.address.city");
        String state = JsonPath.read(response.body(), "$.details.address.state");
        String zip = JsonPath.read(response.body(), "$.details.address.zip");
        String country = JsonPath.read(response.body(), "$.details.address.country");
        String phone = JsonPath.read(response.body(), "$.details.address.phone");
        softAssert.assertEquals("Purchase was successful", result);
        softAssert.assertEquals("Instigate", first_name);
        softAssert.assertEquals( "Mobile", last_name);
        softAssert.assertEquals("90404", zip);
        softAssert.assertEquals( "US", country);
        softAssert.assertNotNull(company);
        softAssert.assertNotNull(name_on_account);
        softAssert.assertNotNull(address1);
        softAssert.assertNotNull(address2);
        softAssert.assertNotNull(city);
        softAssert.assertNotNull(state);
        softAssert.assertNotNull(phone);
        softAssert.assertAll();
    }

    public void getLoginResponseContent(Response response) {
        String email = JsonPath.read(response.body(), "email");
        String given_name = JsonPath.read(response.body(), "given_name");
        String family_name = JsonPath.read(response.body(), "family_name");
        String sub = JsonPath.read(response.body(), "sub");
        String id_token = JsonPath.read(response.body(), "id_token");
        String access_token = JsonPath.read(response.body(), "access_token");
        String refresh_token = JsonPath.read(response.body(), "refresh_token");
        softAssert.assertNotNull(email);
        softAssert.assertEquals(helperMethods.userData.get("email"), email);
        softAssert.assertNotNull(given_name);
        softAssert.assertEquals(helperMethods.userData.get("given_name"), given_name);
        softAssert.assertNotNull(family_name);
        softAssert.assertEquals(helperMethods.userData.get("family_name"), family_name);
        softAssert.assertNotNull(sub);
        softAssert.assertEquals(helperMethods.userData.get("sub"), sub);
        softAssert.assertNotNull(id_token);
        softAssert.assertNotNull(access_token);
        softAssert.assertNotNull(refresh_token);
        softAssert.assertAll();
    }

    public static void compareContent(File actual) {
        File file = new File("/home/arsen_d/Desktop/Openfit-API/OpenfitAPIs/src/test/ContentExpected.json");
        compareFilesContent(actual, file);
    }

    private static void compareFilesContent(File file1, File file2) {
        JsonObject expectedJsonObject = new JsonParser().parse(file1.toString()).getAsJsonObject();
        JsonObject actualJsonObject = new JsonParser().parse(file2.toString()).getAsJsonObject();
        softAssert.assertEquals(expectedJsonObject, actualJsonObject);
        softAssert.assertAll();
    }
}

