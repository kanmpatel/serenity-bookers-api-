package com.herokuapp.booker.cucumber.steps;

import com.herokuapp.booker.bookerinfosteps.BookingSteps;
import com.herokuapp.booker.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

public class MyStepdefs {

    static String firstname = "john" + TestUtils.getRandomValue();
    static String lastname = "smith";
    static int totalprice = 50000;
    static boolean depositpaid = true;
    static HashMap<String,String> bookingdates = new HashMap<>();
    static String additionalneeds = "any";
    static String username = "admin";
    static String password = "password123";
    static int bookingId;
    static String token;
    ValidatableResponse response;

    @Steps
    BookingSteps bookingSteps;

    @When("^I sends the Post Request for create token$")
    public void iSendsThePostRequestForCreateToken() {
    }

    @And("^I insert the username and password$")
    public void iInsertTheUsernameAndPassword() {
        response = bookingSteps.createAuthtication(username,password);

    }

    @Then("^I verify the status code (\\d+) for token$")
    public void iVerifyTheStatusCodeForToken(int statusCode) {
        response.log().all().statusCode(statusCode);
    }

    @And("^I get the token for updating and deleting data$")
    public void iGetTheTokenForUpdatingAndDeletingData() {
        token = response.extract().path("token");
    }

    @When("^I sends the Post Request for create the booking Data$")
    public void iSendsThePostRequestForCreateTheBookingData() {
    }

    @And("^I insert the firstname, lastname,totalprice, depositpaid, bookingdates,additionalneeds$")
    public void iInsertTheFirstnameLastnameTotalpriceDepositpaidBookingdatesAdditionalneeds() {
        bookingdates.put("checkin","2021-09-11");
        bookingdates.put("checkout","2022-05-11");
        response = bookingSteps.createBooking(firstname,lastname,totalprice,depositpaid,bookingdates,additionalneeds);
    }

    @Then("^I verify the status code (\\d+) for booking data$")
    public void iVerifyTheStatusCodeForBookingData(int statusCode) {
        response.log().all().statusCode(statusCode);
    }

    @And("^I get the id of new created booking data$")
    public void iGetTheIdOfNewCreatedBookingData() {
        bookingId = response.extract().path("bookingid");
    }

    @When("^I sends a GET request for fatching booking data by given ID$")
    public void iSendsAGETRequestForFatchingBookingDataByGivenID() {
        response = bookingSteps.getBookingDataByID(bookingId);
    }

    @Then("^I verify the name is for created record for booking$")
    public void iVerifyTheNameIsForCreatedRecordForBooking() {
        response.log().all().statusCode(200);
        response.body("firstname",equalTo(firstname));
    }

    @When("^I send the Put Request for updating the booking data$")
    public void iSendThePutRequestForUpdatingTheBookingData() {

    }

    @And("^I update the irstname, lastname,totalprice, depositpaid, bookingdates,additionalneeds$")
    public void iUpdateTheIrstnameLastnameTotalpriceDepositpaidBookingdatesAdditionalneeds() {
        firstname = firstname + "_updated";
        bookingdates.put("checkin","2021-09-21");
        bookingdates.put("checkout","2022-05-20");
        response = bookingSteps.updateBooking(bookingId,token,firstname,lastname,totalprice,depositpaid,bookingdates,additionalneeds);
    }

    @Then("^I verify the status code (\\d+) for update booking data$")
    public void iVerifyTheStatusCodeForUpdateBookingData(int statusCode) {
        response.log().all().statusCode(statusCode);
    }

    @And("^I verify the name for updating the booking data$")
    public void iVerifyTheNameForUpdatingTheBookingData() {
        response.body("firstname",equalTo(firstname));
    }

    @When("^I sends the Delete Request for deleting the booking  given ID$")
    public void iSendsTheDeleteRequestForDeletingTheBookingGivenID() {
        response = bookingSteps.deleteDataByID(bookingId,token);
    }

    @Then("^I verify the status code (\\d+) for delete booking data$")
    public void iVerifyTheStatusCodeForDeleteBookingData(int statusCode) {
        response.log().all().statusCode(statusCode);
    }

    @And("^I get the data for deleted record ID for booking data$")
    public void iGetTheDataForDeletedRecordIDForBookingData() {
        response = bookingSteps.getBookingDataByID(bookingId);
    }

    @And("^I verify the status code (\\d+) for verifing the delete record$")
    public void iVerifyTheStatusCodeForVerifingTheDeleteRecord(int statusCode) {
        response.log().all().statusCode(statusCode);
    }
}
