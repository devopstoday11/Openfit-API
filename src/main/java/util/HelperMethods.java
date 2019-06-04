package util;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import requests.Response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class HelperMethods {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static HashMap headers = new HashMap();
    public static HashMap userData = new HashMap();
    public static HashMap tokenIDs = new HashMap();
    public static String userDataForRegistration = "{\"items\":[\n" +
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
        String idToken = userJson.getAsString("id_token");
        String access_token = userJson.getAsString("access_token");
        userData.put("access_token","\""+access_token);
        userData.put("given_name","\""+given_name);
        userData.put("family_name","\""+family_name);
        userData.put("id_token", idToken);
        userData.put("email",email);
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
