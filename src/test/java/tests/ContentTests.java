package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import java.io.IOException;

public class ContentTests {

    @Test(groups = "hp")
    @Description("GET content.")
    @Step("Make request to GET content")
    public void getContent() throws IOException {
//        RequestBuilder request = new RequestBuilder("https://content-api.qa.openfit.com")
//                .addHeader("Referer", "https://home.qa.openfit.com/workouts")
//                .addHeader("x-api-key", "dNX8iCVUTV8U7B4brm6ns1cUZ3bkV0it4XpFKgRG")
//                .addHeader("Authorization", "Bearer eyJraWQiOiJpNXdpK29KS2pzbzdWZG0xY20zdUZ4SnVQYUZGSFE4aElkRUptcjQwdkdjPSIsImFsZyI6IlJTMjU2In0.eyJjdXN0b206ZWlkcyI6IntcImVpZHNcIjpbXCJPUEVORklUXCJdfSIsInN1YiI6IjNkMjgzOWMwLThjMTctNDVhNC1iNTMwLTM1NjAxMWE0MWVkNyIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJjdXN0b206Y3VzdG9tZXIiOiJ5IiwiY3VzdG9tOnNob3BpZnlfaWQiOiIyMDIwNjQyNjUyMjI3IiwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLnVzLXdlc3QtMi5hbWF6b25hd3MuY29tXC91cy13ZXN0LTJfMG1OUVhuNDE5IiwiY29nbml0bzp1c2VybmFtZSI6IjNkMjgzOWMwLThjMTctNDVhNC1iNTMwLTM1NjAxMWE0MWVkNyIsImdpdmVuX25hbWUiOiJUZXN0IiwiYXVkIjoiM3Axcm11N2V2czJucjdsdTI3aDc1Y3NuYmYiLCJldmVudF9pZCI6ImEzMDIwZDE1LWVlYmQtNDIxMS1hNjI5LThhYzI3MjRmMGQwZCIsInRva2VuX3VzZSI6ImlkIiwiYXV0aF90aW1lIjoxNTYwNzUxMDgwLCJjdXN0b206ZGlnaV9jb250X3N0YXR1cyI6InBheWluZyIsImV4cCI6MTU2MDc2ODkzMSwiaWF0IjoxNTYwNzY1MzMxLCJmYW1pbHlfbmFtZSI6IlRlc3QiLCJlbWFpbCI6InRlc3RlcnNma3ZnZmxAeW9wbWFpbC5jb20ifQ.SR3E8tFORTd9toEuXLdSnjcjJJtSpc5LL94oyPkrayDynLMxGKFRHNt8sZZ1EMDdE9v34qhIa9_AePF0UmfvpKMeYgIImunDMUVo5n5tVIXesjD1DHtDT1aPOu9hMx5YhkMZ764qR4VR-VB9m7Ed2Fu98t5BJebe9SZDmHYtfRvcms1ogbWybgZH70aPWN81Ng2EMfDDUYqKOIk73WV1IIfS8ASR-cMhV3BKCs5dpe-yayAYO9F9CM3L319o94kiX_zmF1wLKfKDifeyT8qLcAwilLNfuZabc7Pddks6AiThgMzLJh-IYvjc06QqEtvgUYV21yswaYF227PjULcvTA")
//                .addPathParameters("qa", "v1")
//                .addQueryParameter("query", "[(kind=programs,fields=[slug,title,headers,videoCount.label,videoCount.value,description,duration,equipment,intensityLevel.slug,intensityLevel.title,resources.category,resources.items,videoGroups.slug,videoGroups.title,videoGroups.videos.guid,videoGroups.videos.description,videoGroups.videos.program.slug,videoGroups.videos.program.title,videoGroups.videos.slug,videoGroups.videos.title,videoGroups.videos.duration,videoGroups.videos.requiredEquipment,videoGroups.videos.recommendedEquipment,videoGroups.videos.workoutLevels.slug,videoGroups.videos.workoutLevels.title,videoGroups.videos.videoTypes.slug])]");
//        Response response = request.get();
//        System.out.println(response.getCurl());
//        File file = new File("/home/arsen_d/Desktop/Openfit-API/OpenfitAPIs/src/test/Actual.json");
//        FileWriter fileWriter = new FileWriter(file.getPath());
//        fileWriter.write(response.body());
//        CheckerMethods.compareContent(file);
    }
}
