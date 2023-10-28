package keywords;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

import restassured.steps.APISteps;

public class APIKeywords {
    @Steps
    APISteps api;

    @Given("the valid endpoint to test")
    public void theValidEndpointToTest() {
        api.setEndpoint();
    }


    //GET STATUS
    @When("the request is sent to the server with status path")
    public void theRequestIsSentToTheServerWithStatusPath() {
        api.sendStatusPathGetRequest();
    }

    @Then("the response code received is 200 and body status is OK")
    public void theResponseCodeReceivedIsAndBodyStatusIsOK() {
        api.verifyStatusPathCodeAndBodyReceived();
    }
    //----------------------------------


    //POST AUTHENTICATION
    @When("the request is sent to the server with the authentication path and the correct JSON body")
    public void theRequestIsSentToTheServerWithTheAuthenticationPathAndTheCorrectJSONBody() {
        api.authSendJSONBodyAndGetTokenPostRequest();
    }

    @Then("the response code received is 201 and body contains the accessToken")
    public void theResponseCodeReceivedIsAndBodyContainsTheAccessToken() {
        api.tokenReceived();
    }
    //----------------------------------


    //GET LIST OF BOOKS
    @When("the request is sent to the server with the get list of books path and type and limit params")
    public void theRequestIsSentToTheServerWithTheGetListOfBooksPathAndTypeAndLimitParams() {
        api.sendListOfBooksPathGetRequest("non-fiction", 2);
    }

    @Then("the response code received is 200 and body contains a list with id, name, type and available")
    public void theResponseCodeReceivedIsAndBodyContainsAListWithIdNameTypeAndAvailable() {
        api.verifyListOfBooksPathStatusCodeAndBodyReceived();
    }
    //----------------------------------


    //GET SINGLE BOOK
    @When("the request is sent to the server with the get a single book path")
    public void theRequestIsSentToTheServerWithTheGetASingleBookPath() {
        api.sendSingleBookPathGetRequest();
    }

    @Then("the response code received is 200 and body contains a list with id, name, author, type, price, current-stock and available")
    public void theResponseCodeReceivedIsAndBodyContainsAListWithIdNameAuthorTypePriceCurrentStockAndAvailable() {
        api.verifySingleBookPathStatusCodeAndBodyReceived();
    }
    //----------------------------------


    //POST ORDER BOOK
    @When("the request is sent to the server with the order book path and the correct JSON body")
    public void theRequestIsSentToTheServerWithTheOrderBookPathAndTheCorrectJSONBody() {
        api.orderBookPostRequest();
    }

    @Then("the response code received is 201 and body contains a list with created and orderId")
    public void theResponseCodeReceivedIsAndBodyContainsAListWithCreatedAndOrderId() {
        api.verifyOrderBookPathStatusCodeAndBodyReceived();
    }
    //----------------------------------


    //GET BOOK ORDERS
    @When("the request is sent to the server with the get orders path")
    public void theRequestIsSentToTheServerWithTheGetOrdersPath() {
        api.sendBookOrdersPathGetRequest();
    }

    @Then("the response code received is 200 and body contains a list with id, bookId, customerName, createdBy, quantity and timestamp")
    public void theResponseCodeReceivedIsAndBodyContainsAListWithIdBookIdCustomerNameCreatedByQuantityAndTimestamp() {
        api.verifyBookOrdersPathBodyReceived();
    }
    //----------------------------------


    //GET SINGLE BOOK ORDER
    @When("the request is sent to the server with the get single order path")
    public void theRequestIsSentToTheServerWithTheGetSingleOrderPath() {
        api.sendSingleBookOrderPathGetRequest();
    }

    @Then("the response code received is 200 and body contains the correct id, bookId, customerName and createdBy")
    public void theResponseCodeReceivedIsAndBodyContainsTheCorrectIdBookIdCustomerNameAndCreatedBy() {
        api.verifySingleBookOrderPathBodyReceived();
    }
    //----------------------------------


    //PATCH BOOK ORDER
    @When("the request is sent to the server with the patch get single order path and the correct JSON body")
    public void theRequestIsSentToTheServerWithThePatchGetSingleOrderPathAndTheCorrectJSONBody() {
        api.sendSingleBookOrderPathPatchRequest();
    }

    @Then("the response code received is 204 and the order is updated")
    public void theResponseCodeReceivedIsAndTheOrderIsUpdated() {
        api.verifySingleBookOrderPathPatchStatusCodeAndUpdate();
    }
    //----------------------------------


    //DELETE BOOK ORDER
    @When("the request is sent to the server with the delete get single order path")
    public void theRequestIsSentToTheServerWithTheDeleteGetSingleOrderPath() {
        api.sendSingleBookOrderPathDeleteRequest();
    }

    @Then("the response code received is 204 and the order is deleted")
    public void theResponseCodeReceivedIsAndTheOrderIsDeleted() {
        api.verifySingleBookOrderStatusCodeAndDelete();
    }
}
