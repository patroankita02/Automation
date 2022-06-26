package stepDefinitions;

import static org.junit.Assert.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StepDefintions extends Utils {
    RequestSpecification req;
    ResponseSpecification responseObj;
    Response result;
    RequestSpecification res;
    TestDataBuild data = new TestDataBuild();
    static String placeid;
    JsonPath js;
    @Given("Add place payload {string} {string} {string}")
    public void addPlacePayload(String name, String language, String address)  throws IOException
    {

        res= given().spec(requestSpecification()).body(data.AddPlacePayload(name,language,address));
    }

    @When("user calls {string} with {string} http request")
    public void userCallsWithHttpRequest(String resource, String httpMethod)
    {
        APIResources resourceapi = APIResources.valueOf(resource);
        System.out.println(resourceapi.getResources());
        responseObj = new ResponseSpecBuilder().expectStatusCode(200).
                expectContentType(ContentType.JSON).build();
        if(httpMethod.equalsIgnoreCase("POST"))
        {
            result = res.when().post(resourceapi.getResources()).then().spec(responseObj).extract().response();
        }
        if(httpMethod.equalsIgnoreCase("GET"))
        {
            result = res.when().get(resourceapi.getResources()).then().spec(responseObj).extract().response();
        }
        if(httpMethod.equalsIgnoreCase("DELETE"))
        {
            result = res.when().delete(resourceapi.getResources()).then().spec(responseObj).extract().response();
        }
        //result = res.when().post(resourceapi.getResources()).then().spec(responseObj).extract().response();
    }

    @Then("The API call is success with status code {int}")
    public void theAPICallIsSuccessWithStatusCode(int arg0)
    {
        assertEquals(result.getStatusCode(),200);
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String key, String value)
    {
        String response = result.asString();
        assertEquals(getJsonPath(result,key),value);
    }


    @And("verify place_id created maps to {string} using {string}")
    public void verifyPlace_idCreatedMapsToUsing(String expectedname, String resourceName) throws IOException {
//        String response = result.asString();
        placeid=getJsonPath(result,"place_id");
        res= given().spec(requestSpecification()).queryParam("place_id",placeid);
        userCallsWithHttpRequest(resourceName,"GET");
        String name = getJsonPath(result,"name");
        assertEquals(name,expectedname);
    }

    @Given("DeletePlace payload")
    public void deleteplacePayload() throws IOException {
        res = given().spec(requestSpecification()).body(data.deletePlacePayload(placeid));
    }
}
