import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
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

import java.io.*;
import java.util.HashMap;


public class Methods {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static HashMap headers = new HashMap();
    public static HashMap userData = new HashMap();
    public static HashMap tokenIDs = new HashMap();
    public static String userDataForRegistration = "{\"items\":[\n" +
            "\t{\n" +
            "\t\"product_id\":1685587853379,\n" +
            "\t\"variant_id\":15447916838979,\n" +
            "\t\"quantity\":1}],\n" +
            "\t\"token_id\":"+Methods.tokenIDs.get("token_id")+",\n" +
            "\t\"access_token\":"+Methods.userData.get("access_token")+"\",\n" +
            "\t\"shipping_address\":\n" +
            "\t\t{\"first_name\":"+Methods.userData.get("given_name")+"\",\n" +
            "\t\t\"last_name\":"+Methods.userData.get("family_name")+"\",\n" +
            "\t\t\"state\":\"CA\",\n" +
            "\t\t\"city\":\"Santa Monica\",\n" +
            "\t\t\"address1\":\"3301 Exposition Blvd\",\n" +
            "\t\t\"zip\":\"90404-5045\"\n" +
            "\t\t},\"shipping_method\":\"on\"\n" +
            "\t\n" +
            "}";

    public static void headers(String authorization){
        headers.put("x-api-key","WYvuNYNwZo8LapYoJwwwh18rgJt85tPB8iYDAXSr");
        headers.put("Content-Type","application/json");
        headers.put("Authorization","Bearer "+authorization);
    }

    public static void getUser(Response response) throws ParseException {
        JSONObject userJson = (JSONObject) new JSONParser().parse(response.body());
        String given_name = userJson.getAsString("given_name");
        String family_name = userJson.getAsString("family_name");
        String email = userJson.getAsString("email");
        String sub = userJson.getAsString("sub");
        String idToken = userJson.getAsString("id_token");
        String access_token = userJson.getAsString("access_token");
        String refresh_token = userJson.getAsString("refresh_token");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(given_name,"Test");
        softAssert.assertEquals(family_name,"Test");
        softAssert.assertNotNull(given_name);
        softAssert.assertNotNull(family_name);
        softAssert.assertNotNull(email);
        softAssert.assertNotNull(sub);
        softAssert.assertNotNull(idToken);
        softAssert.assertNotNull(access_token);
        softAssert.assertNotNull(refresh_token);
        softAssert.assertAll();
        userData.put("access_token","\""+access_token);
        userData.put("given_name","\""+given_name);
        userData.put("family_name","\""+family_name);
        userData.put("id_token", idToken);
        userData.put("email",email);
    }

    public static void getTokenId(Response response) throws ParseException{
        JSONObject tokenIdjson = (JSONObject) new JSONParser().parse(response.body());
        String idToken = tokenIdjson.getAsString("id");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(idToken);
        tokenIDs.put("token_id","\""+idToken+"\"");

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

    public static void getSubscriptionContent(Response response) throws ParseException, IOException {
        JSONObject getUserData = (JSONObject) new JSONParser().parse(Methods.userDataForRegistration);
        JSONArray data = (JSONArray) new JSONParser().parse(response.body());
        JSONObject subscriptions = (JSONObject) data.get(0);
        String dataType []= {"first_name", "last_name", "state", "city", "address1", "zip"};
        String subscription_canceled_at = JsonPath.read(subscriptions.toJSONString(),"$.canceled_at");
        System.out.println(subscription_canceled_at);
        String subscription_plan_code = JsonPath.read(subscriptions.toJSONString(),"$.plan_code").toString();
        System.out.println(subscription_plan_code);
        String subscription_plan_name = JsonPath.read(subscriptions.toJSONString(),"$.plan_name").toString();
        System.out.println(subscription_plan_name);
        String subscription_source = JsonPath.read(subscriptions.toJSONString(),"$.source").toString();
        System.out.println(subscription_source);
        String subscription_type = JsonPath.read(subscriptions.toJSONString(),"$.type").toString();
        System.out.println(subscription_type);
        String subscription_id = JsonPath.read(subscriptions.toJSONString(),"$.id").toString();
        System.out.println(subscription_id);
        String subscription_country = JsonPath.read(subscriptions.toJSONString(),"$.country").toString();
        System.out.println(subscription_country);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(subscription_canceled_at);
        softAssert.assertEquals(subscription_plan_code,getUserData.getAsString("product_id")+"-"+getUserData.getAsString("variant_id"));
        softAssert.assertEquals(subscription_plan_name,"Openfit Streaming Subscription... - Semi-Annual");
        softAssert.assertEquals(subscription_source, "Shopify");
        softAssert.assertEquals(subscription_type,"digital");
        softAssert.assertEquals(subscription_country,"US");
        softAssert.assertNotNull(subscription_id);
        for (int i = 0; i <dataType.length; i++){
            System.out.println(getUserData.getAsString(dataType[i]) + " " + subscriptions.getAsString(dataType[i]));
            softAssert.assertEquals(getUserData.getAsString(dataType[i]),subscriptions.getAsString(dataType[i]));
        }
        softAssert.assertAll();
    }

    public static void getLoginResponseContent(Response response) throws ParseException, IOException {
        JSONObject loginContent = (JSONObject) new JSONParser().parse(response.body());
        File file = new File("/home/arsen_d/Desktop/OpenfitAPIs/src/test/LoginContent.json");
        FileWriter fileWriter = new FileWriter("LoginContent.json");
        fileWriter.write(loginContent.toJSONString());
        fileWriter.close();
        String [] data = {"email", "given_name, family_name, sub, id_token, access_token, refresh_token"};
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < data.length; i++) {
            softAssert.assertEquals(loginContent.getAsString(data[i]),userData.get(data[i]));
        }
        softAssert.assertAll();

    }

    public static String fileReader(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }return contentBuilder.toString();
    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
