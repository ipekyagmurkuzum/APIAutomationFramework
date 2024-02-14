package api.test;

import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static api.endpoints.UserEndpoint.*;

public class UserTests {

    Faker faker;
    User userPayload;
    @BeforeClass
    public void setupData(){
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void testPostUser() {
        Response response = createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test(priority = 2)
    public void testGetUserByName(){
        Response response = readUser(userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getBody().jsonPath().getJsonObject("username"),userPayload.getUsername());
    }

    @Test(priority = 3)
    public void testUpdateUserInfo(){
        userPayload.setPhone("999-999-99-99");
        Response response = updateUser(userPayload,userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);

        Response responseAfterUpdate = readUser(userPayload.getUsername());
        Assert.assertEquals(responseAfterUpdate.getBody().jsonPath().getJsonObject("phone"),"999-999-99-99");
    }

    @Test(priority = 4)
    public void testDeleteUser(){
        Response response = deleteUser(userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);
    }
}
