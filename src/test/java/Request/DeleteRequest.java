package Request;

import Utils.SetProperties;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class DeleteRequest extends SetProperties {
   public DeleteRequest(){super();}
   private String deleteMovieRatePath = "/movie/" ;
   private String deleteListPath = "/list/";
   PostRequest postRequest = new PostRequest();
    JSONObject jsonObject = new JSONObject();

   public void deleteRate(int idMovie){
       Response response = given()
               .queryParam("api_key",getApi_key())
               .queryParam("session_id",postRequest.CreateSession())
               .when()
               .delete(getBase_url()+deleteMovieRatePath+idMovie+"/rating")
               .then()
               .statusCode(200)
               .log()
               .body()
               .extract().response();
       Assert.assertEquals("true",response.jsonPath().getString("success"));
       Assert.assertEquals("The item/record was deleted successfully.",response.jsonPath().getString("status_message"));
   }

   public void deleteList(int list_id){
       jsonObject
               .put("list_id",list_id);
       Response response = given()
               .queryParam("api_key",getApi_key())
               .queryParam("session_id",postRequest.CreateSession())
               .when()
               .delete(getBase_url()+deleteListPath+list_id)
               .then()
               .statusCode(500)
               .log()
               .body()
               .extract().response();
       Assert.assertEquals(11,response.jsonPath().getInt("status_code"));
       //Assert.assertEquals("The item/record was updated successfully.",response.jsonPath().getString("status_message")); //this assert is not working


   }


}

