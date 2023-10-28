package restassured.steps;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import restassured.util.PropertyHelper;

import static net.serenitybdd.rest.RestRequests.post;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

import static net.serenitybdd.rest.RestRequests.given;
import static org.hamcrest.Matchers.*;

public class APISteps {
    private Response response;
    private String statusPath = "status";
    private String apiAuthPath = "api-clients/";
    private String booksListPath = "books"; //single book: books/<id>
    private String bookOrdersPath = "orders"; //single order: orders/<id>

    @Step
    public void setEndpoint () {
        RestAssured.baseURI = "https://simple-books-api.glitch.me";
        /*response = given().when().get();
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("message", equalTo("Welcome to the Simple Books API."));*/
    }

    @Step
    public void sendStatusPathGetRequest () {
        response = given().when().get(statusPath);
    }

    @Step
    public void verifyStatusPathCodeAndBodyReceived() {
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("status", equalTo("OK"));

    }

    private String accessToken = "";
    private Response postAuth;

    @Step
    public void authSendJSONBodyAndGetTokenPostRequest() {
        JSONObject jsonData = new JSONObject();
        jsonData.put("clientName", customerName);
        jsonData.put("clientEmail", "user" + PropertyHelper.readVariableValueInt("counter") + "@example.com");
        PropertyHelper.incrementVariable("counter");

        postAuth = given()
                .contentType(ContentType.JSON)
                .body(jsonData.toString())
                .when()
                .post(apiAuthPath);

        accessToken = postAuth
                .then()
                .extract()
                .path("accessToken")
                .toString();

        PropertyHelper.writeVariableValue("accessToken", accessToken);
    }

    @Step
    public void tokenReceived () {
        postAuth.then().assertThat()
                .statusCode(201);
        assertThat(postAuth.getBody().asString().contains("accessToken")).as("Body contains accessToken").isTrue();
    }

    @Step
    public void sendListOfBooksPathGetRequest () {
        response = given().
                when().
                get(booksListPath);
    }

    @Step
    public void sendListOfBooksPathGetRequest (String type, int limit) {
        response = given().
                param("type", type).
                param("limit", limit).
                when().
                get(booksListPath);
    }

    private int bookId = 0;

    @Step
    public void verifyListOfBooksPathStatusCodeAndBodyReceived() {
        response.then().assertThat().statusCode(200);

        List<Map<String, Object>> books = response.as(new TypeRef<List<Map<String, Object>>>() {});

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).get("available").equals(true)) {
                bookId = Integer.parseInt(books.get(i).get("id").toString());
                PropertyHelper.writeVariableValue("bookId", bookId);
                break;
            }
        }

        assertThat(response.getBody().asString().contains("id")).as("Body contains id").isTrue();
        assertThat(response.getBody().asString().contains("name")).as("Body contains name").isTrue();
        assertThat(response.getBody().asString().contains("type")).as("Body contains type").isTrue();
        assertThat(response.getBody().asString().contains("available")).as("Body contains available").isTrue();
    }

    private String singleBookPath = "";

    @Step
    public void sendSingleBookPathGetRequest () {
        singleBookPath = booksListPath + "/" + PropertyHelper.readVariableValueInt("bookId");
        response = given().when().get(singleBookPath);
    }

    @Step
    public void verifySingleBookPathStatusCodeAndBodyReceived() {
        response.then().assertThat().statusCode(200);
        assertThat(response.getBody().asString().contains("id")).as("Body contains id").isTrue();
        assertThat(response.getBody().asString().contains("name")).as("Body contains name").isTrue();
        assertThat(response.getBody().asString().contains("author")).as("Body contains author").isTrue();
        assertThat(response.getBody().asString().contains("type")).as("Body contains type").isTrue();
        assertThat(response.getBody().asString().contains("price")).as("Body contains price").isTrue();
        assertThat(response.getBody().asString().contains("current-stock")).as("Body contains current-stock").isTrue();
        assertThat(response.getBody().asString().contains("available")).as("Body contains available").isTrue();

    }

    private String orderId = "";
    private Response postOrder;
    private final String customerName = "Andrei";
    @Step
    public void orderBookPostRequest() {
        JSONObject jsonData = new JSONObject();
        jsonData.put("bookId", PropertyHelper.readVariableValueInt("bookId"));
        jsonData.put("customerName", customerName);

        postOrder = given()
                .auth()
                .oauth2(PropertyHelper.readVariableValueString("accessToken"))
                .contentType(ContentType.JSON)
                .body(jsonData.toString())
                .when()
                .post(bookOrdersPath);

        orderId = postOrder
                .then()
                .extract()
                .path("orderId")
                .toString();

        PropertyHelper.writeVariableValue("orderId", orderId);
    }

    @Step
    public void verifyOrderBookPathStatusCodeAndBodyReceived() {
        postOrder.then().assertThat()
                .statusCode(201);
        assertThat(postOrder.getBody().asString().contains("created")).as("Body contains created").isTrue();
        assertThat(postOrder.getBody().asString().contains("orderId")).as("Body contains orderId").isTrue();
    }

    @Step
    public void sendBookOrdersPathGetRequest () {
        response = given()
                .auth()
                .oauth2(PropertyHelper.readVariableValueString("accessToken"))
                .when()
                .get(bookOrdersPath);
    }

    @Step
    public void verifyBookOrdersPathBodyReceived () {
        assertThat(response.getBody().asString().contains("id")).as("Body contains id").isTrue();
        assertThat(response.getBody().asString().contains("bookId")).as("Body contains bookId").isTrue();
        assertThat(response.getBody().asString().contains("customerName")).as("Body contains customerName").isTrue();
        assertThat(response.getBody().asString().contains("createdBy")).as("Body contains createdBy").isTrue();
        assertThat(response.getBody().asString().contains("quantity")).as("Body contains quantity").isTrue();
        assertThat(response.getBody().asString().contains("timestamp")).as("Body contains timestamp").isTrue();
    }

    private final String singleBookOrderPath = bookOrdersPath + "/" + PropertyHelper.readVariableValueString("orderId");

    @Step
    public void sendSingleBookOrderPathGetRequest () {
        response = given()
                .auth()
                .oauth2(PropertyHelper.readVariableValueString("accessToken"))
                .when()
                .get(singleBookOrderPath);
    }

    @Step
    public void verifySingleBookOrderPathBodyReceived () {
        response.then()
                .assertThat()
                .body("id", equalTo(PropertyHelper.readVariableValueString("orderId")))
                .body("bookId", equalTo(PropertyHelper.readVariableValueInt("bookId")))
                .body("customerName", equalTo(customerName));
    }

    private final String patchedName = "Andrei Patched";

    @Step
    public void sendSingleBookOrderPathPatchRequest () {
        JSONObject jsonData = new JSONObject();
        jsonData.put("customerName", patchedName);

        response = given()
                .auth()
                .oauth2(PropertyHelper.readVariableValueString("accessToken"))
                .contentType(ContentType.JSON)
                .body(jsonData.toString())
                .when()
                .patch(singleBookOrderPath);
    }

    @Step
    public void verifySingleBookOrderPathPatchStatusCodeAndUpdate() {
        response.then().assertThat()
                .statusCode(204);
        sendSingleBookOrderPathGetRequest();
        response.then()
                .assertThat()
                .body("customerName", equalTo(patchedName));
    }

    @Step
    public void sendSingleBookOrderPathDeleteRequest () {
        response = given()
                .auth()
                .oauth2(PropertyHelper.readVariableValueString("accessToken"))
                .when()
                .delete(singleBookOrderPath);
    }

    @Step
    public void verifySingleBookOrderStatusCodeAndDelete () {
        response.then().assertThat()
                .statusCode(204);
        sendSingleBookOrderPathGetRequest();
        response.then().assertThat()
                .statusCode(404);
        assertThat(response.getBody().asString().contains("error")).as("Order deleted").isTrue();
    }
}
