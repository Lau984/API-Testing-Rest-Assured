package Test;

import Request.DeleteRequest;
import Request.GetRequest;

import Request.PostRequest;
import org.testng.annotations.Test;

public class TMDB {
    int list_id=8202673;
    @Test
    public void RequestToken(){
        GetRequest getRequests = new GetRequest();
        getRequests.getToken();
    }

    @Test
    public void ValidateWithLogin(){
        PostRequest postRequest = new PostRequest();
        postRequest.validateToken();
    }

    @Test
    public void CreateSession(){
        PostRequest postRequest = new PostRequest();
        postRequest.CreateSession();
    }

    @Test
    public void RateMovie(){
        PostRequest postRequest = new PostRequest();
        postRequest.RateMovie(671,9.5);
    }

    @Test
    public void DeleteRateMovie(){
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.deleteRate(671);
    }

    //task
    @Test
    public void CreateList(){
        PostRequest postRequest = new PostRequest();
        postRequest.List("ListRestAssured","This list was created automatically","en");
    }

    @Test
    public void AddMovietoList(){
        PostRequest postRequest = new PostRequest();
        postRequest.AddMovie(list_id,453395);
    }

    @Test
    public void GetListStatus(){
        GetRequest getRequests = new GetRequest();
        getRequests.getList(list_id);
    }

    @Test
    public void GetItemStatus(){ //NOT WORKING :c
        GetRequest getRequests = new GetRequest();
        getRequests.getItemStatus(list_id,1);
    }
    @Test
    public void DeleteList(){
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.deleteList(list_id); //52-63
    }


}
