Feature: Validating the place API
  @AddPlace @Regression
  Scenario Outline: Verify place is added successfully
    Given Add place payload "<name>" "<language>" "<address"
    When user calls "AddPlaceAPI" with "post" http request
    Then The API call is success with status code 200
    And "status" in response body is "OK"
    And verify place_id created maps to "<name>" using "getPlaceAPI"
  Examples:
    |name|language|address|
    |Ank |English |California|
    |Ran |English |Newyork   |

    @DeletePlace @Regression
  Scenario: Verify if delete place functionality is working
    Given DeletePlace payload
    When user calls "deletePlaceAPI" with "post" http request
    Then The API call is success with status code 200
    And "status" in response body is "OK"