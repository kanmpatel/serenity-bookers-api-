package com.herokuapp.booker.bookerinfosteps;

import com.herokuapp.booker.constants.EndPoints;
import com.herokuapp.booker.model.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class BookingSteps {

    @Step
    public ValidatableResponse createAuthtication(String username, String password){
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setUsername(username);
        bookingPojo.setPassword(password);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(bookingPojo)
                .when()
                .post(EndPoints.CREATE_AUTH)
                .then();
    }

    @Step
    public ValidatableResponse createBooking(String firstname, String lastname, int totalprice,
                                             boolean depositpaid, HashMap<String,String> bookingdates,
                                             String additionalneeds){
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setBookingdates(bookingdates);
        bookingPojo.setAdditionalneeds(additionalneeds);


        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(bookingPojo)
                .when()
                .post(EndPoints.CREATE_BOOKING)
                .then();
    }

    @Step
    public ValidatableResponse getBookingDataByID(int bookingId){
        return SerenityRest.given()
                .pathParam("bookerID",bookingId)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING)
                .then();
    }

    @Step
    public ValidatableResponse updateBooking(int bookingId ,String token ,String firstname, String lastname, int totalprice,
                                             boolean depositpaid, HashMap<String,String> bookingdates,
                                             String additionalneeds){
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setBookingdates(bookingdates);
        bookingPojo.setAdditionalneeds(additionalneeds);

        return SerenityRest.given()
                .header("Cookie","token=" + token + "")
                .header("Authorization","Bearer " + token + "")
                .pathParam("bookerID",bookingId)
                .contentType(ContentType.JSON)
                .body(bookingPojo)
                .when()
                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then();
    }

    @Step
    public ValidatableResponse deleteDataByID(int bookingId,String token){
        return SerenityRest.given()
                .header("Cookie","token=" + token + "")
                .pathParam("bookerID",bookingId)
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then();
    }


}
