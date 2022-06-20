Feature: End to End testing of booking data

  Scenario: Create Token for updating and deleting data
    When    I sends the Post Request for create token
    And     I insert the username and password
    Then    I verify the status code 200 for token
    And     I get the token for updating and deleting data

  Scenario: Create a new booking data & verify if the booking is added
    When    I sends the Post Request for create the booking Data
    And     I insert the firstname, lastname,totalprice, depositpaid, bookingdates,additionalneeds
    Then    I verify the status code 200 for booking data
    And     I get the id of new created booking data


  Scenario: Verify the booking data are successfully created
    When I sends a GET request for fatching booking data by given ID
    Then I verify the name is for created record for booking

  Scenario: Update the booking data for given ID
    When    I send the Put Request for updating the booking data
    And     I update the irstname, lastname,totalprice, depositpaid, bookingdates,additionalneeds
    Then    I verify the status code 200 for update booking data
    And     I verify the name for updating the booking data

  Scenario: Delete the Prodct data for given ID & verify the record are deleted successfully
    When    I sends the Delete Request for deleting the booking  given ID
    Then    I verify the status code 201 for delete booking data
    And     I get the data for deleted record ID for booking data
    And     I verify the status code 404 for verifing the delete record










