import org.json.JSONException;
import org.testng.annotations.Test;
import requests.RequestBuilder;
import requests.Response;
import util.CheckerMethods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ContentTests {

    @Test
    public void getContent() throws IOException, JSONException {
        RequestBuilder request = new RequestBuilder("https://content-api.qa.openfit.com")
                .addHeader("Referer","https://home.qa.openfit.com/workouts")
                .addHeader("x-api-key", "dNX8iCVUTV8U7B4brm6ns1cUZ3bkV0it4XpFKgRG")
                .addHeader("Authorization", "Bearer eyJraWQiOiJpNXdpK29KS2pzbzdWZG0xY20zdUZ4SnVQYUZGSFE4aElkRUptcjQwdkdjPSIsImFsZyI6IlJTMjU2In0.eyJjdXN0b206ZWlkcyI6IntcImVpZHNcIjpbXCJPUEVORklUXCJdfSIsInN1YiI6ImNhM2UyNzk4LTg2NTgtNDFmYy1iZmFlLThkOWQxOGEzMDBjOSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJjdXN0b206Y3VzdG9tZXIiOiJ5IiwiY3VzdG9tOnNob3BpZnlfaWQiOiIyMDA1MTY1MjQ0NDgzIiwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLnVzLXdlc3QtMi5hbWF6b25hd3MuY29tXC91cy13ZXN0LTJfMG1OUVhuNDE5IiwiY29nbml0bzp1c2VybmFtZSI6ImNhM2UyNzk4LTg2NTgtNDFmYy1iZmFlLThkOWQxOGEzMDBjOSIsImdpdmVuX25hbWUiOiJJbnN0aWdhdGUiLCJhdWQiOiIzcDFybXU3ZXZzMm5yN2x1MjdoNzVjc25iZiIsImV2ZW50X2lkIjoiODczZjg5ZTAtYWZkZC00NTA2LWEyMzQtYWY4MGNkMGEyODliIiwidG9rZW5fdXNlIjoiaWQiLCJhdXRoX3RpbWUiOjE1NjAzNDUwNTEsImN1c3RvbTpkaWdpX2NvbnRfc3RhdHVzIjoicGF5aW5nIiwiZXhwIjoxNTYwMzQ4NzU3LCJpYXQiOjE1NjAzNDUxNTcsImZhbWlseV9uYW1lIjoiTW9iaWxlIiwiZW1haWwiOiJmZG5mc2pkZm4wc2RAeW9wbWFpbC5jb20ifQ.lJo_pDvwp6WTKO9TUz2Wlcz79W1mbDlWiuzZ9G9mK3IHz8g9YnAV5turQBxZfmm9opMG-jtYS5H1TanvHNSuxW9QLz5csF957nfZ6JqLc5NWCrQfAIjzhZS8ks1flp1XBHXDzS3L7B8UPtLp3xjmZBKCD9MTE9B2yS7JLwsMCRjLypRyeljqUq7ydR89dMYIVBObR5gd6xtDe3OTOQMlmvSSL0tuEucGxWXXo0FGMFVdn3lqYQot8sMyyUVSfPRo4EgwATHid2bpfo-fkzdDfuMwpWoZ6vOb9CI7HR7zKsNT3uiOzPmDq908PzWofPI8nFINhwxHCJwJrG0nBl9sWQ")
                .addPathParameters("qa", "v1")
                .addQueryParameter("query","[(kind=programs,fields=[slug,title,headers,videoCount.label,videoCount.value,description,duration,equipment,intensityLevel.slug,intensityLevel.title,resources.category,resources.items,videoGroups.slug,videoGroups.title,videoGroups.videos.guid,videoGroups.videos.description,videoGroups.videos.program.slug,videoGroups.videos.program.title,videoGroups.videos.slug,videoGroups.videos.title,videoGroups.videos.duration,videoGroups.videos.requiredEquipment,videoGroups.videos.recommendedEquipment,videoGroups.videos.workoutLevels.slug,videoGroups.videos.workoutLevels.title,videoGroups.videos.videoTypes.slug])]");
        Response response = request.get();
        System.out.println(response.getCurl());
        File file = new File("/home/arsen_d/Desktop/Openfit-API/OpenfitAPIs/src/test/Actual.json");
        FileWriter fileWriter = new FileWriter(file.getPath());
        fileWriter.write(response.body());
        CheckerMethods.compareContent(file);
    }
}
