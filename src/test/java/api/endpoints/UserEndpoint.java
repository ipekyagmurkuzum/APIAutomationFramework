package api.endpoints;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static api.endpoints.Routes.*;
import static io.restassured.RestAssured.given;

public class UserEndpoint {

    public static Response createUser(User payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(postUrl);
        return response;
    }

    public static Response readUser(String username) {
        Response response = given()
                .pathParam("username", username)
                .when()
                .get(getUrl);
        return response;
    }

    public static Response updateUser(User payload, String username) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(putUrl);
        return response;
    }

    public static Response deleteUser(String username) {
        Response response = given()
                .pathParam("username", username)
                .when()
                .delete(deleteUrl);
        return response;
    }


}
