package util;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.asserts.SoftAssert;
import requests.RequestBuilder;
import requests.Response;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Checker {
    static SoftAssert softAssert = new SoftAssert();

    public static void getTokenId(Response response) throws ParseException{
        JSONObject tokenIdjson = (JSONObject) new JSONParser().parse(response.body());
        String idToken = tokenIdjson.getAsString("id");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(idToken);
        HelperMethods.tokenIDs.put("token_id","\""+idToken+"\"");

    }

    public static void getFreeRegisterContent(Response response) throws ParseException, IOException {
        JSONObject freeRegisterContent = (JSONObject) new JSONParser().parse(response.body());
        String email = freeRegisterContent.getAsString("email");
        String given_name = freeRegisterContent.getAsString("given_name");
        String family_name = freeRegisterContent.getAsString("family_name");
        String sub = freeRegisterContent.getAsString("sub");
        String id_token = freeRegisterContent.getAsString("id_token");
        String access_token = freeRegisterContent.getAsString("access_token");
        String refresh_token = freeRegisterContent.getAsString("refresh_token");
        softAssert.assertEquals(given_name,"Test");
        softAssert.assertEquals(family_name,"Test");
        softAssert.assertNotNull(email);
        softAssert.assertNotNull(sub);
        softAssert.assertNotNull(id_token);
        softAssert.assertNotNull(access_token);
        softAssert.assertNotNull(refresh_token);
        softAssert.assertAll();
    }

    public static void getSubscriptionContent(Response response) throws ParseException, IOException {
        JSONObject subscriptionContent = (JSONObject) new JSONParser().parse(response.body());
        String access_token = subscriptionContent.getAsString("message");
    }

    public static void getLoginResponseContent(Response response) throws ParseException, IOException {
        JSONObject loginContent = (JSONObject) new JSONParser().parse(response.body());
        String [] data = {"email", "given_name, family_name, sub, id_token, access_token, refresh_token"};
        for (int i = 0; i < data.length; i++) {
            softAssert.assertEquals(loginContent.getAsString(data[i]), HelperMethods.userData.get(data[i]));
        }
        softAssert.assertAll();
    }

    public static void doRequestAndCompare(String path) throws IOException, JSONException {
        RequestBuilder requestBuilder = new RequestBuilder("https://content-api.qa.openfit.com");
        requestBuilder.addPathParameters("qa", "v1")
                .addQueryParameter("query", "[(kind=videos,fields=[guid,description,program.slug,program.title,slug,title,duration,requiredEquipment,recommendedEquipment,workoutLevels.slug,workoutLevels.title,videoTypes.slug])]")
                .addHeader("x-api-key", "dNX8iCVUTV8U7B4brm6ns1cUZ3bkV0it4XpFKgRG")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer eyJraWQiOiJpNXdpK29KS2pzbzdWZG0xY20zdUZ4SnVQYUZGSFE4aElkRUptcjQwdkdjPSIsImFsZyI6IlJTMjU2In0.eyJjdXN0b206ZWlkcyI6IntcImVpZHNcIjpbXCJPUEVORklUXCJdfSIsInN1YiI6Ijk4Mzk4YjNjLTQ1NWEtNGM5NS1hOGM1LTg2ODYwMmRiYzNmZiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJjdXN0b206Y3VzdG9tZXIiOiJ5IiwiY3VzdG9tOnNob3BpZnlfaWQiOiIxNDMyOTg2NTgzMTA3IiwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLnVzLXdlc3QtMi5hbWF6b25hd3MuY29tXC91cy13ZXN0LTJfMG1OUVhuNDE5IiwiY29nbml0bzp1c2VybmFtZSI6Ijk4Mzk4YjNjLTQ1NWEtNGM5NS1hOGM1LTg2ODYwMmRiYzNmZiIsImdpdmVuX25hbWUiOiJJbnN0aWdhdGUiLCJhdWQiOiIzcDFybXU3ZXZzMm5yN2x1MjdoNzVjc25iZiIsImV2ZW50X2lkIjoiYzMzNTgzOTgtNzcxYS0xMWU5LTk0ZjktMjdkMjNjYTMyNzUwIiwidG9rZW5fdXNlIjoiaWQiLCJhdXRoX3RpbWUiOjE1NTc5MjkyNDQsImN1c3RvbTpkaWdpX2NvbnRfc3RhdHVzIjoicGF5aW5nIiwiZXhwIjoxNTU3OTMyODQ0LCJpYXQiOjE1NTc5MjkyNDQsImZhbWlseV9uYW1lIjoiTW9iaWxlIiwiZW1haWwiOiJtcWExMUB5b3BtYWlsLmNvbSJ9.K3rtwKXIFFpZgXiCKFRUdszzfPZd8f1sZd7Y8GOjd67K10GncrfVw5HVdutiWRY2TC42OxG6Lzwvr9O9NUHsqTLLuzyBmUh4B0PF0OlPTQm32lhqqNCKDWETzglII-6BYNJATz1nXY4DZVdiNUjcXUFJTENlU_lV8t-ikl-hFqYElHVyzHtfK6Rs5BZImwTLUx1DVWvFC7DZE4hhxvcL5bRkArccd7IqoIEVsO5J8DRc48Pk8puvstSOA_V304SDSwYIfdxwE2VWKJVA8FDOx7-rjnGI5XCyG-cSK2Lt2LhCLvWETrrGd9gnIrBv_5pdn1dn_QQjyoidIlCJC80oZA");
        Response qaResponse = requestBuilder.get();
        System.out.println(qaResponse.getCurl());
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(qaResponse.body());
        fileWriter.close();
        compareFilesContent(new File("/home/arsen_d/Desktop/OpenfitAPIs/src/test/programs.txt"), new File(path));
    }


    public static void compareFilesContent(File file1, File file2) throws IOException, JSONException {
        String expectedJson = FileUtils.readFileToString(file1);
        String actualJson = FileUtils.readFileToString(file2);
        JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);
    }
}
