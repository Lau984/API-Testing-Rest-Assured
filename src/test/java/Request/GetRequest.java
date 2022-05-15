package Request;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import Utils.SetProperties;

import static io.restassured.RestAssured.given;

public class GetRequest extends SetProperties {
    public GetRequest() {super();}
    private String token_path="/authentication/token/new";
    private String itemStatus_path="/list/";
    JSONObject jsonObject = new JSONObject();



    public String getToken(){
        Response response = given()
                .queryParam("api_key", getApi_key())
                .when()
                .get(getBase_url()+token_path)
                .then()
                .statusCode(200)
                .log()
                .body()
                .extract()
                .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        return response.jsonPath().getString("request_token");
    }

    public void getList(int list_id){
        jsonObject
                .put("list_id",list_id);
        Response response =given()
                .queryParam("api_key", getApi_key())
                .when()
                .get(getBase_url()+itemStatus_path+list_id)
                .then()
                //.statusCode(200)
                .log()
                .body()
                .extract().response();
        Assert.assertEquals("lau98",response.jsonPath().getString("created_by"));



    }
    public void getItemStatus(int list_id, int movie_id){ //NOT WORKING
        jsonObject
                .put("list_id",list_id)
                .put("movie_id",movie_id);
        Response response = given()
                .queryParam("api_key", getApi_key())
                .when()
                .get(getBase_url()+"/list/8202666/item_status")
                .then()
                //.statusCode(200)
                .log()
                .body()
                .extract().response();
        Assert.assertEquals("true",response.jsonPath().getString("item_present"));

    }
}
