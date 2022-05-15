package Request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class PostRequest extends Utils.SetProperties {
    public PostRequest(){super();}
    private String loginPath ="/authentication/token/validate_with_login";
    private String createSessionPath ="/authentication/session/new";
    private String rateMoviePath ="/movie/";
    private  String listPath="/list";
    private String addMoviePath ="/list/";
    GetRequest getRequest = new GetRequest();
    JSONObject jsonObject = new JSONObject();

    public String validateToken(){
        jsonObject
                .put("username", getUsername())
                .put("password", getPassword())
                .put("request_token",getRequest.getToken());
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url()+ loginPath)
                .then()
                .statusCode(200)
                //.log()
                //.body()
                .extract()
                .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        return response.jsonPath().getString("request_token");
    }

    public String CreateSession(){
        jsonObject
                .put("request_token",validateToken());
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url()+ createSessionPath)
                .then()
                .statusCode(200)
                .log()
                .body()
                .extract().response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        return response.jsonPath().getString("session_id");
    }

    public void RateMovie(int idMovie,double rate ){
        jsonObject
                .put("value",rate);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .queryParam("session_id",CreateSession())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url()+rateMoviePath+idMovie+"/rating")
                .then()
                .statusCode(201)
                .log()
                .body()
                .extract().response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
    }

    public void List(String name, String description,String language ){
        jsonObject
                .put("name",name)
                .put("description",description)
                .put("language",language);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .queryParam("session_id",CreateSession())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url()+listPath)
                .then()
                .statusCode(201)
                .log()
                .body()
                .extract().response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        Assert.assertEquals("The item/record was created successfully.",response.jsonPath().getString("status_message"));
    }

    public void AddMovie(int list_id, int media_id ){
        jsonObject
                .put("list_id",list_id)
                .put("media_id",media_id);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .queryParam("session_id",CreateSession())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url()+addMoviePath+list_id+"/add_item")
                .then()
                //.statusCode(201)
                .log()
                .body()
                .extract().response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        Assert.assertEquals("The item/record was updated successfully.",response.jsonPath().getString("status_message"));
    }


}
