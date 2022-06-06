package com.herokuapp.booker.bookerinfo;

import com.herokuapp.booker.bookerinfosteps.BookingSteps;
import com.herokuapp.booker.testbase.TestBase;
import com.herokuapp.booker.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class BookingCurdTest extends TestBase {

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

    @Test
    public void test001(){
        response = bookingSteps.createAuthtication(username,password);
        response.log().all().statusCode(200);
        token = response.extract().path("token");
    }

    @Test
    public void test002(){
        bookingdates.put("checkin","2021-09-11");
        bookingdates.put("checkout","2022-05-11");
        response = bookingSteps.createBooking(firstname,lastname,totalprice,depositpaid,bookingdates,additionalneeds);
        response.log().all().statusCode(200);
        bookingId = response.extract().path("bookingid");
        System.out.println(bookingId);
    }

    @Test
    public void test003(){
        response = bookingSteps.getBookingDataByID(bookingId);
        response.log().all().statusCode(200);
        response.body("firstname",equalTo(firstname));
    }

    @Test
    public void test004(){
        firstname = firstname + "_updated";
        bookingdates.put("checkin","2021-09-21");
        bookingdates.put("checkout","2022-05-20");
        response = bookingSteps.updateBooking(bookingId,token,firstname,lastname,totalprice,depositpaid,bookingdates,additionalneeds);
        response.log().all().statusCode(200);
        response.body("firstname",equalTo(firstname));

    }

    @Test
    public void test005(){
        response = bookingSteps.deleteDataByID(bookingId,token);
        response.log().all().statusCode(201);

        response = bookingSteps.getBookingDataByID(bookingId);
        response.log().all().statusCode(405);
    }

}
