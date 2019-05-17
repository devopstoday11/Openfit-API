import io.qameta.allure.Step;
import net.minidev.json.parser.ParseException;
import org.testng.annotations.Test;
import requests.RequestBuilder;
import requests.Response;
import java.io.IOException;


public class APITest {


    @Test(priority = 0, description = "Make request to GET token ID.")
    @Step("Make request to generate valid ID Token: {0}")
    public void getIDToken() throws IOException, ParseException {
        RequestBuilder requestBuilder = new RequestBuilder("https://api.recurly.com");
        requestBuilder.addPathParameters("js","v1","token");
        Response response = requestBuilder.post("{\"first_name\": \"Instigate\", \"last_name\": \"Mobile\", \"postal_code\": \"90404\", \"country\": \"US\", \"number\": \"4111111111111111\",\"month\": \"12\", \"year\": \"2020\", \"cvv\": \"132\", \"key\": \"ewr1-bBXWWGAuZMiIT4CL76bvwL\" }","application/json");
        Methods.getTokenId(response);
    }

    @Test(priority = 0, description = "Make request to subscribe with 6m.")
    @Step("Make request to generate valid ID Token: {0}, Make request to register free user: {1}, Make request to Subscribe with valid credentials: {2}")
    public void Registration6m() throws IOException, ParseException {
        getIDToken();
        freeRegister();
        Methods.headers((String) Methods.userData.get("id_token"));
        RequestBuilder requestBuilder = new RequestBuilder("https://connect-checkout-be.qa.openfit.com");
        requestBuilder.addPathParameters("v1", "subscriptions", "new")
                .addHeader(Methods.headers);
        Response response = requestBuilder.post("{\"items\":[\n" +
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
                "}");
        System.out.println("Registration 1m:\n"+response.getCurl());
        System.out.println("\nResponse: " +response.body());
    }

    @Test(priority = 0, description = "Free register with valid credentials.")
    @Step("Make request to create free user: {0}")
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

    @Test(priority = 0, description = "Login with valid credentials.")
    @Step("Subscribe with valid product ID: {0}, Login with valid credentials: {1}")
    public void login() throws IOException, ParseException {
        Registration6m();
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "login")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP");
        Response response = requestBuilder.post("{\"email\": \""+Methods.userData.get("email")+"\",\"password\":\"Test1234@\"}", "application/json");
        System.out.println(response.getCurl());
        System.out.println(response.body());

    }

    @Test(priority = 0, description = "GET Subscription after Subscribing.")
    @Step("Free Registration: {0}, Generated Token ID: {1}, Subscribe with valid product ID: {2}")
    public void getSubscriptions() throws IOException, ParseException {
        Registration6m();
        String autorization = (String) Methods.headers.get("Authorization");
        RequestBuilder requestBuilder = new RequestBuilder("https://connect-account-be.qa.openfit.com");
        requestBuilder.addPathParameters("v1", "subscriptions")
                .addHeader("x-api-key", "ZUL7kSands2gzEHb8ZB7X1onCufpTkqJ3S3h5AlK")
                .addHeader("Authorization", autorization);
        Response response = requestBuilder.get();
        System.out.println("GET Subscriptions:\n"+response.getCurl());
        System.out.println("\nResponse: " +response.body());
       // Methods.getSubscriptionContent(response);
    }

    @Test(priority = 0, description = "Change attributes.")
    @Step("Make request to create free user: {0}, Make request to generate valid token ID: {1}, Make request to subscribe with valid credentials: {2}, Make reqeust to change user attributes: {3}")
    public void changeAttributes() throws IOException, ParseException {
        Registration6m();
        String autorization = (String) Methods.headers.get("Authorization");
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "changeattributes")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP")
                .addHeader("Authorization", autorization);
        Response response = requestBuilder.post("{\n" +
                "        \"email\":"+Methods.userData.get("email")+"\",\n" +
                "        \"first_name\":\"Updated\",\n" +
                "        \"last_name\":\"Name\"\n" +
                "        \n" +
                "}", "application/json");

        System.out.println(response.getCurl());
        System.out.println(response.body());
    }

    @Test(priority = 0, description = "Change password.")
    @Step("Make request to create free user: {0}, Make request to generate valid token ID: {1}, Make request to subscribe with valid credentials: {2}, Make reqeust to change user password: {3}")
    public void changePassword() throws IOException {
        RequestBuilder requestBuilder = new RequestBuilder(" https://connect-customer.qa.openfit.com")
                .addPathParameters("v1", "changepassword")
                .addHeader("x-api-key", "wrlyU1ZZUL1QSl1BKe7zw9ZsTJANTCAe7mRh2WLP")
                .addHeader("Authorization", "Bearer eyJraWQiOiJ0YXF6bkc4SklGNjN1cUJqTHE5cGppTDhVNmZSbkp1bmNKYkxNbGFYMndZPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI2NzU2OGI4Yi1kOWZjLTRmZjgtOThlNS1mMTExNTUxNGNkYjIiLCJldmVudF9pZCI6IjQ3NDdhZTA0LTcyZjMtMTFlOS05NGY5LTI3ZDIzY2EzMjc1MCIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE1NTc0NzI0ODEsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC51cy13ZXN0LTIuYW1hem9uYXdzLmNvbVwvdXMtd2VzdC0yXzBtTlFYbjQxOSIsImV4cCI6MTU1NzQ3NjE5NCwiaWF0IjoxNTU3NDcyNTk0LCJqdGkiOiI5N2RlYTVkYy0zZTljLTQ2ZTUtOWFkZS01YjE3NWU2NDE4MjUiLCJjbGllbnRfaWQiOiIzcDFybXU3ZXZzMm5yN2x1MjdoNzVjc25iZiIsInVzZXJuYW1lIjoiNjc1NjhiOGItZDlmYy00ZmY4LTk4ZTUtZjExMTU1MTRjZGIyIn0.d1Ey1P62bZrXyuE3swUz_MefaM7L2q_rKK6Mih_VTC3iFmWvBlaFbl7JwCti5mqAWbBvc_mML7xGfITvuhP_Zsk-vG34rE5sIX36LCKRFL_UXBvR3kn_tbYX5AN7FkhlGUtiwmOgJ_RxapIPchMmd7comjzy3zXv3BDJmwyIJsGn0gqc4jP82l6sezFc4JFVCcRaHxrvyR6Lqao8GxpbpvjqJuAQN8CPc4ZEVRDTnqq3_RXnz_Imn-WxjneP51HTLEsh6xWPyoFstPuAQBg--Od4_5h5jPGpNrZKGElegMWRcm45HfYhvlMkCVZDkzl5-Xnrm9Tp6lqESgdCldTSZw")
                .addHeader("Content-Type", "application/json");
        Response response = requestBuilder.post("previousPassword\":\"Test1234Test!\",\"proposedPassword\":\"Test1234!");
        System.out.println(response.getCurl());
        System.out.println(response.body());
    }

//    @Test
//    public void ProgramMaterialStaticData() throws IOException, JSONException {
//        Methods.doRequestAndCompare("/home/arsen_d/Desktop/OpenfitAPIs/src/test/programsTest.txt");
//    }



}

