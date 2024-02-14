package api.test;

import api.payloads.User;
import api.utils.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static api.endpoints.UserEndpoint.*;
import static api.endpoints.UserEndpoint.readUser;

public class DataDrivenUserTests {

    User userPayload;

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userId, String username, String firstName, String lastName, String email, String password, String phone) {
        userPayload = new User();
        userPayload.setId(Integer.parseInt(userId));
        userPayload.setUsername(username);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        Response response = createUser(userPayload);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2,dataProvider = "UserData", dataProviderClass = DataProviders.class)
    public void testGetUser(String username){
        Response response = readUser(username);
        response.then().log().all();
        Assert.assertEquals(response.getBody().jsonPath().getJsonObject("username"),username);
    }

    @Test(priority = 2,dataProvider = "UserData", dataProviderClass = DataProviders.class)
    public void testUpdateUser(String username){
        userPayload.setPhone("999-999-99-99");
        Response response = updateUser(userPayload,username);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);

        Response responseAfterUpdate = readUser(username);
        Assert.assertEquals(responseAfterUpdate.getBody().jsonPath().getJsonObject("phone"),"999-999-99-99");
    }
    @Test(priority = 2,dataProvider = "UserData", dataProviderClass = DataProviders.class)
    public void testDeleteUser(String username){
        Response response = deleteUser(username);
        Assert.assertEquals(response.statusCode(),200);
    }
}
