package api.endpoints;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static api.endpoints.Routes.*;
import static io.restassured.RestAssured.given;

public class UserEndpointsFromProperties {

    static ResourceBundle getURLsFromProperties(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }

    public static Response createUser(User payload) {
        String postUrl = getURLsFromProperties().getString("postUrl");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(postUrl);
        return response;
    }

    public static Response readUser(String username) {
        String getUrl = getURLsFromProperties().getString("getUrl");
        Response response = given()
                .pathParam("username", username)
                .when()
                .get(getUrl);
        return response;
    }

    public static Response updateUser(User payload, String username) {
        String putUrl = getURLsFromProperties().getString("putUrl");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
                .when()
                .put(putUrl);
        return response;
    }

    public static Response deleteUser(String username) {
        String deleteUrl = getURLsFromProperties().getString("deleteUrl");
        Response response = given()
                .pathParam("username", username)
                .when()
                .delete(deleteUrl);
        return response;
    }


}
