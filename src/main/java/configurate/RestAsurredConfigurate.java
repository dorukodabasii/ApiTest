package configurate;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class RestAsurredConfigurate {

    public RequestSpecification getRequestSpecification() {

        return RestAssured.given().contentType(ContentType.JSON);
    }

    public Response getResponse(RequestSpecification specification, String endpoint, int
            status){

        Response response = specification.get(endpoint);
        Assert.assertEquals(response.getStatusCode(),status);


        //Loglama yapıldı.
        response.then().log().all();

        return response;
    }

}
