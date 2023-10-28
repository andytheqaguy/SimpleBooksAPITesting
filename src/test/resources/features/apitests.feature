@ApiTests
Feature: API tests

  @getstatus
  Scenario: Simple Books API - API Status
    Given the valid endpoint to test
    When the request is sent to the server with status path
    Then the response code received is 200 and body status is OK

  @postauthentication
  Scenario: Simple Books API - API Authentication
    Given the valid endpoint to test
    When the request is sent to the server with the authentication path and the correct JSON body
    Then the response code received is 201 and body contains the accessToken

  @getlistofbooks
  Scenario: Simple Books API - Get list of books
    Given the valid endpoint to test
    When the request is sent to the server with the get list of books path and type and limit params
    Then the response code received is 200 and body contains a list with id, name, type and available

  @getsinglebook
  Scenario: Simple Books API - Get a single book
    Given the valid endpoint to test
    When the request is sent to the server with the get a single book path
    Then the response code received is 200 and body contains a list with id, name, author, type, price, current-stock and available

  @postorderbook
  Scenario: Simple Books API - Order a book
    Given the valid endpoint to test
    When the request is sent to the server with the order book path and the correct JSON body
    Then the response code received is 201 and body contains a list with created and orderId

  @getbookorders
  Scenario: Simple Books API - Get book orders
    Given the valid endpoint to test
    When the request is sent to the server with the get orders path
    Then the response code received is 200 and body contains a list with id, bookId, customerName, createdBy, quantity and timestamp

  @getsinglebookorder
  Scenario: Simple Books API - Get a single book order
    Given the valid endpoint to test
    When the request is sent to the server with the get single order path
    Then the response code received is 200 and body contains the correct id, bookId, customerName and createdBy

  @patchupdatebookorder
  Scenario: Simple Books API - Update a book order
    Given the valid endpoint to test
    When the request is sent to the server with the patch get single order path and the correct JSON body
    Then the response code received is 204 and the order is updated

  @deletebookorder
  Scenario: Simple Books API - Delete a book order
    Given the valid endpoint to test
    When the request is sent to the server with the delete get single order path
    Then the response code received is 204 and the order is deleted