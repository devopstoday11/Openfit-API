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
    public HashMap userData = new HashMap();
    public HashMap tokenIDs = new HashMap();

    public static void headers(String authorization) {
        headers.put("x-api-key", "WYvuNYNwZo8LapYoJwwwh18rgJt85tPB8iYDAXSr");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + authorization);
    }

    public void getUser(Response response) throws ParseException {
        JSONObject userJson = (JSONObject) new JSONParser().parse(response.body());
        String given_name = userJson.getAsString("given_name");
        String family_name = userJson.getAsString("family_name");
        String email = userJson.getAsString("email");
        String idToken = userJson.getAsString("id_token");
        String access_token = userJson.getAsString("access_token");
        String refresh_token = userJson.getAsString("refresh_token");
        String sub = userJson.getAsString("sub");
        userData.put("sub", sub);
        userData.put("access_token", access_token);
        userData.put("refresh_token", refresh_token);
        userData.put("given_name", given_name);
        userData.put("family_name", family_name);
        userData.put("id_token", idToken);
        userData.put("email", email);
    }

    public static String fileReader(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
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
