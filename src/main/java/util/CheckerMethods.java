package util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.testng.asserts.SoftAssert;
import requests.Response;

import java.io.File;
import java.io.IOException;

public class CheckerMethods {
    static SoftAssert softAssert = new SoftAssert();

    public static void getTokenIdResponseContent(Response response) throws ParseException {
        JSONObject tokenIdjson = (JSONObject) new JSONParser().parse(response.body());
        String idToken = tokenIdjson.getAsString("id");
        String type = tokenIdjson.getAsString("type");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(idToken);
        softAssert.assertNotNull(type);
        softAssert.assertEquals(type, "credit_card");
        HelperMethods.tokenIDs.put("token_id", "\"" + idToken + "\"");

    }

    public static void getFreeRegisterResponseContent(Response response) throws ParseException, IOException {
        JSONObject freeRegisterContent = (JSONObject) new JSONParser().parse(response.body());
        String email = freeRegisterContent.getAsString("email");
        String given_name = freeRegisterContent.getAsString("given_name");
        String family_name = freeRegisterContent.getAsString("family_name");
        String sub = freeRegisterContent.getAsString("sub");
        String id_token = freeRegisterContent.getAsString("id_token");
        String access_token = freeRegisterContent.getAsString("access_token");
        String refresh_token = freeRegisterContent.getAsString("refresh_token");
        softAssert.assertEquals(given_name, "Test");
        softAssert.assertEquals(family_name, "Test");
        softAssert.assertNotNull(email);
        softAssert.assertNotNull(sub);
        softAssert.assertNotNull(id_token);
        softAssert.assertNotNull(access_token);
        softAssert.assertNotNull(refresh_token);
        softAssert.assertAll();
    }

    public static void getChangeAttributesResponseContent(Response response) throws ParseException {
        JSONObject getChangeAttributesResponseContent = (JSONObject) new JSONParser().parse(response.body());
        String success = getChangeAttributesResponseContent.getAsString("success");
        softAssert.assertNotNull(success);
        softAssert.assertEquals(success, "true");
        softAssert.assertAll();
    }

    public static void getChangePasswordResponseContent(Response response) throws ParseException {
        JSONObject getChangePasswordResponseContent = (JSONObject) new JSONParser().parse(response.body());
        String success = getChangePasswordResponseContent.getAsString("success");
        softAssert.assertNotNull(success);
        softAssert.assertEquals(success, "true");
        softAssert.assertAll();
    }

    public static void getSubscriptionResponseContent(Response response) throws ParseException, IOException {
        JSONObject subscriptionContent = (JSONObject) new JSONParser().parse(response.body());
        String access_token = subscriptionContent.getAsString("message");
    }

    public static void getLoginResponseContent(Response response) throws ParseException, IOException {
        JSONObject loginContent = (JSONObject) new JSONParser().parse(response.body());
        String[] data = {"email", "given_name, family_name, sub, id_token, access_token, refresh_token"};
        for (int i = 0; i < data.length; i++) {
            softAssert.assertEquals(loginContent.getAsString(data[i]), HelperMethods.userData.get(data[i]));
        }
        softAssert.assertAll();
    }

    public static void compareContent(File actual){
        File file = new File("/home/arsen_d/Desktop/Openfit-API/OpenfitAPIs/src/test/ContentExpected.json");
        try {
            compareFilesContent(actual, file);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private static void compareFilesContent(File file1, File file2) throws ParseException {
        JsonObject expectedJsonObject = new JsonParser().parse(file1.toString()).getAsJsonObject();
        JsonObject actualJsonObject = new JsonParser().parse(file1.toString()).getAsJsonObject();
            softAssert.assertEquals(expectedJsonObject, actualJsonObject);
        softAssert.assertAll();
    }
}

